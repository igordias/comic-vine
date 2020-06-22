package dev.igordias.comicvineapp.core.domain.util

import dev.igordias.comicvineapp.core.data.remote.request.envelope.ResponseEnvelope
import dev.igordias.comicvineapp.core.data.remote.request.envelope.ResponseEnvelope.Companion.HTTP_CODE_UNKNOWN
import dev.igordias.comicvineapp.core.data.remote.request.error.HttpErrorType


sealed class RequestException private constructor(
    val title: String,
    override val message: String
) : Throwable() {

    class Http(response: ResponseEnvelope<*>?) :
        RequestException("ERROR", response?.errorMessage ?: "") {
        val code: Int = response?.statusCode ?: HTTP_CODE_UNKNOWN
        val type = HttpErrorType.fromCode(code)
    }

    // TODO: Use user friendly messages, use Strings
    class Network() : RequestException("NETWORK ERROR", "A network error happened")
    class Timeout() : RequestException("TIMEOUT ERROR", "Timeout error")
    class Unexpected() : RequestException("UNEXPECTED ERROR", "Unexpected error")
    class NullBody() : RequestException("BODY CANT BE NULL", "Response Body was null")
}