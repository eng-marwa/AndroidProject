package com.marwa.androidproject.data.datasource.remote.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val CONNECT_TIME_OUT = 5
private const val WRITE_TIME_OUT = 5
private const val READ_TIME_OUT = 5
private val TIME_UNIT = TimeUnit.SECONDS

object RetrofitClient {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun provideOkHttp(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().readTimeout(READ_TIME_OUT.toLong(), TIME_UNIT).writeTimeout(
            WRITE_TIME_OUT.toLong(), TIME_UNIT
        ).connectTimeout(CONNECT_TIME_OUT.toLong(), TIME_UNIT).addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
}

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().addHeader("accept", "application/json").build()
        return chain.proceed(req)
    }

}