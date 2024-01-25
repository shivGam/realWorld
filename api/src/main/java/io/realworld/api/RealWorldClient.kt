package io.realworld.api

import io.realworld.api.services.RealWorldAPI
import io.realworld.api.services.RealWorldAuthAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RealWorldClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.realworld.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(RealWorldAPI::class.java)
}