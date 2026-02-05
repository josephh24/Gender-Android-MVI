package com.example.mviproyect.data.network.repository

import com.example.mviproyect.data.network.model.GenderUserResponse

interface NetworkDataSource {

    suspend fun fetchNetworkGenderUser(name: String): GenderUserResponse
}