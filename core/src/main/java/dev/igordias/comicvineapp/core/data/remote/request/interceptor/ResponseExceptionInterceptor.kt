package dev.igordias.comicvineapp.core.data.remote.request.interceptor

import dev.igordias.comicvineapp.core.data.remote.request.RequestHandler
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseExceptionInterceptor : Interceptor, RequestHandler() {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        try {
            return chain.proceed(chain.request())
        } catch (e: Exception) {
            throw e.asRequestException()
        }
    }

}