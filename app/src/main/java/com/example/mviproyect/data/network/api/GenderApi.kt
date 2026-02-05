package com.example.mviproyect.data.network.api

import com.example.mviproyect.data.network.model.GenderUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderApi {

    @GET("/")
    suspend fun fetchGenderUser(@Query("name") name: String): GenderUserResponse
}