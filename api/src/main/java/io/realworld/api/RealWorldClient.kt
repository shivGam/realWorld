package io.realworld.api

import io.realworld.api.services.RealWorldAPI
import io.realworld.api.services.RealWorldAuthAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RealWorldClient {

    var authToken:String?= null

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization","Token $it")
                .build()
        }
        chain.proceed(req)
    }
    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5,TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS)
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.realworld.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpBuilder.build())
        .build()

    val publicApi = retrofit.create(RealWorldAPI::class.java)

    val authApi = retrofit.create(RealWorldAuthAPI::class.java)

}