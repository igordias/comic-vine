package dev.igordias.comicvineapp.core.data.remote.request.interceptor

import dev.igordias.comicvineapp.core.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().run {
            val urlWithAuthQueries = url().addAuthQueries()

            newBuilder()
                .url(urlWithAuthQueries)
                .build()
        }

        return chain.proceed(request)
    }

    private fun HttpUrl.addAuthQueries(): HttpUrl = this.newBuilder()
        .addQueryParameter(NAMED_API_KEY_PARAMETER, apiKey)
        .addQueryParameter(NAMED_RESPONSE_FORMAT_PARAMETER, responseFormat)
        .build()

    companion object {
        private const val NAMED_API_KEY_PARAMETER = "api_key"
        private const val NAMED_RESPONSE_FORMAT_PARAMETER = "format"

        private const val apiKey = BuildConfig.API_KEY
        private const val responseFormat = "json"
    }

}