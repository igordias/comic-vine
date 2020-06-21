package dev.igordias.comicvineapp.core.data.inject

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.igordias.comicvineapp.core.BuildConfig
import dev.igordias.comicvineapp.core.data.remote.RetrofitClient
import dev.igordias.comicvineapp.core.data.remote.RetrofitService
import dev.igordias.comicvineapp.core.data.remote.request.envelope.EnvelopeConverter
import dev.igordias.comicvineapp.core.data.remote.request.interceptor.AuthInterceptor
import dev.igordias.comicvineapp.core.data.remote.request.interceptor.ResponseExceptionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.get
import org.rewedigital.katana.dsl.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private enum class NamedBinding {
    AUTH_INTERCEPTOR,
    LOGGING_INTERCEPTOR,
    RETROFIT,
    HTTP_CLIENT,
    RESPONSE_EXCEPTION_INTERCEPTOR
}

val INJECT_MODULE_REMOTE_CLIENT = Module {

    singleton(name = NamedBinding.LOGGING_INTERCEPTOR) {
        val loggingLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
        HttpLoggingInterceptor().setLevel(loggingLevel)
    }

    singleton(name = NamedBinding.AUTH_INTERCEPTOR) {
        AuthInterceptor()
    }

    singleton(name = NamedBinding.RESPONSE_EXCEPTION_INTERCEPTOR) {
        ResponseExceptionInterceptor()
    }

    singleton(name = NamedBinding.HTTP_CLIENT) {
        OkHttpClient.Builder().run {
            addInterceptor(get(NamedBinding.LOGGING_INTERCEPTOR))
            addInterceptor(get(NamedBinding.AUTH_INTERCEPTOR))
            addInterceptor(get(NamedBinding.RESPONSE_EXCEPTION_INTERCEPTOR))
            build()
        }
    }

    singleton(name = NamedBinding.RETROFIT) {
        val builder = Retrofit.Builder()
            .client(get(NamedBinding.HTTP_CLIENT))
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(EnvelopeConverter)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
        builder.build()
    }

    singleton {
        get<Retrofit>(NamedBinding.RETROFIT).create(RetrofitService::class.java)
    }

    singleton {
        RetrofitClient()
    }
}

val INJECT_COMPONENT_REMOTE_CLIENT = Component(modules = listOf(INJECT_MODULE_REMOTE_CLIENT))
