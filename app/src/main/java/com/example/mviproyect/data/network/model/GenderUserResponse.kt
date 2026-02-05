package com.example.mviproyect.data.network.model

import com.example.mviproyect.data.local.entity.GenderUserEntity
import kotlinx.serialization.Serializable

@Serializable
data class GenderUserResponse(
    val count: Int,
    val gender: String,
    val name: String?,
    val probability: Float
)

fun GenderUserResponse.asEntity() = GenderUserEntity(
    count = count,
    gender = gender,
    name = name.orEmpty(),
    probability = probability
)