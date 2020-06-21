package dev.igordias.comicvineapp.core.data.remote.request

import dev.igordias.comicvineapp.core.domain.util.RequestException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class RequestHandler {

    fun <T> Response<T>.handle(): T {
        try {
            return this.body() ?: throw RequestException.NullBody()
        } catch (e: Exception) {
            throw e.asRequestException()
        }
    }

    @Throws(RequestException::class)
    fun Exception.asRequestException(): RequestException = when (this) {
        is RequestException -> this
        is SocketTimeoutException -> RequestException.Timeout()
        is UnknownHostException -> RequestException.Network()
        is IOException -> RequestException.Network()
        else -> RequestException.Unexpected()
    }
}