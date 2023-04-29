package com.alexnechet.remote.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val HEADER_AUTHORIZATION = "api"
private const val AUTH_INTERCEPTOR = "authInterceptor"
private const val LOGGING_INTERCEPTOR = "loggingInterceptor"

fun networkModule(parameters: NetworkParameters) = module {
    single(named(AUTH_INTERCEPTOR)) {
        Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader(HEADER_AUTHORIZATION, parameters.apiKey)
            chain.proceed(requestBuilder.build())
        }
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    single {
        val builder = OkHttpClient.Builder()
        if (parameters.debuggable) {
            builder.addInterceptor(get<Interceptor>(named(LOGGING_INTERCEPTOR)))
        }
        builder.addInterceptor(get<Interceptor>(named(AUTH_INTERCEPTOR)))
        builder.build()
    }

    single { Moshi.Builder().add(get()).build() }

    single<JsonAdapter.Factory> { KotlinJsonAdapterFactory() }

    single<Converter.Factory> { MoshiConverterFactory.create(get()) }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(parameters.baseUrl)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(PixabayApi::class.java) }
}
