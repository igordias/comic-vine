package dev.igordias.comicvineapp.core.data.remote.request.envelope

import com.squareup.moshi.Types
import dev.igordias.comicvineapp.core.domain.util.RequestException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object EnvelopeConverter : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val envelopedType = Types.newParameterizedType(ResponseEnvelope::class.java, type)
        val delegate: Converter<ResponseBody, ResponseEnvelope<Any>>? =
            retrofit.nextResponseBodyConverter(this, envelopedType, annotations)
        return UnwrappedResponse(delegate)
    }

    private class UnwrappedResponse<T>(
        private val delegate: Converter<ResponseBody, ResponseEnvelope<T>>?
    ) : Converter<ResponseBody, T> {
        override fun convert(value: ResponseBody): T? {
            val body = delegate?.convert(value)

            return when (body?.isSuccessful) {
                true -> body.results
                else -> throw RequestException.Http(body)
            }
        }
    }
}