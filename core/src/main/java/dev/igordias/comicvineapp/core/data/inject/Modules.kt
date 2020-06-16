package dev.igordias.comicvineapp.core.data.inject

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.igordias.comicvineapp.core.BuildConfig
import dev.igordias.comicvineapp.core.data.remote.RetrofitClient
import dev.igordias.comicvineapp.core.data.remote.RetrofitService
import dev.igordias.comicvineapp.core.data.remote.request.envelope.EnvelopeConverter
import dev.igordias.comicvineapp.core.data.remote.request.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.get
import org.rewedigital.katana.dsl.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private enum class ProviderName {
    AUTH_INTERCEPTOR,
    LOGGING_INTERCEPTOR,
    RETROFIT,
    HTTP_CLIENT
}

val remoteClientModule = Module {

    singleton(name = ProviderName.LOGGING_INTERCEPTOR) {
        val loggingLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
        HttpLoggingInterceptor().setLevel(loggingLevel)
    }

    singleton(name = ProviderName.AUTH_INTERCEPTOR) {
        AuthInterceptor()
    }

    singleton(name = ProviderName.HTTP_CLIENT) {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(get(ProviderName.LOGGING_INTERCEPTOR))
        builder.addInterceptor(get(ProviderName.AUTH_INTERCEPTOR))
        builder.build()
    }

    singleton(name = ProviderName.RETROFIT) {
        val builder = Retrofit.Builder()
            .client(get(ProviderName.HTTP_CLIENT))
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
        get<Retrofit>(ProviderName.RETROFIT).create(RetrofitService::class.java)
    }

    singleton {
        RetrofitClient()
    }
}

val remoteComponent = Component(modules = listOf(remoteClientModule))