package dev.igordias.comicvineapp.core.data.remote.request

import dev.igordias.comicvineapp.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val parametrizedUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(NAMED_API_KEY_PARAMETER, apiKey)
            .addQueryParameter(NAMED_RESPONSE_FORMAT_PARAMETER, responseFormat)
            .build()

        val requestBuilder = original.newBuilder()
            .url(parametrizedUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val NAMED_API_KEY_PARAMETER = "api_key"
        private const val NAMED_RESPONSE_FORMAT_PARAMETER = "format"

        private const val apiKey = BuildConfig.API_KEY
        private const val responseFormat = "json"
    }

}