package dev.igordias.comicvineapp.core.data.inject

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.igordias.comicvineapp.core.BuildConfig
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

private enum class RemoteModuleNamedBinding {
    AUTH_INTERCEPTOR,
    LOGGING_INTERCEPTOR,
    RETROFIT,
    HTTP_CLIENT,
    RESPONSE_EXCEPTION_INTERCEPTOR
}

val INJECT_MODULE_REMOTE = Module {

    singleton(name = RemoteModuleNamedBinding.LOGGING_INTERCEPTOR) {
        val loggingLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
        HttpLoggingInterceptor().setLevel(loggingLevel)
    }

    singleton(name = RemoteModuleNamedBinding.AUTH_INTERCEPTOR) {
        AuthInterceptor()
    }

    singleton(name = RemoteModuleNamedBinding.RESPONSE_EXCEPTION_INTERCEPTOR) {
        ResponseExceptionInterceptor()
    }

    singleton(name = RemoteModuleNamedBinding.HTTP_CLIENT) {
        OkHttpClient.Builder().run {
            addInterceptor(get(RemoteModuleNamedBinding.AUTH_INTERCEPTOR))
            addInterceptor(get(RemoteModuleNamedBinding.RESPONSE_EXCEPTION_INTERCEPTOR))
            build()
        }
    }

    singleton(name = RemoteModuleNamedBinding.RETROFIT) {
        val builder = Retrofit.Builder()
            .client(get(RemoteModuleNamedBinding.HTTP_CLIENT))
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
        get<Retrofit>(RemoteModuleNamedBinding.RETROFIT).create(RetrofitService::class.java)
    }
}

val INJECT_COMPONENT_REMOTE_CLIENT = Component(modules = listOf(INJECT_MODULE_REMOTE))
