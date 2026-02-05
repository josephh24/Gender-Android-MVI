package com.example.mviproyect.data.local.repository

import com.example.mviproyect.data.local.entity.GenderUserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseResource {

    fun getGenderUserEntity(name: String): Flow<GenderUserEntity>

    suspend fun saveGenderUserEntity(genderUserEntity: GenderUserEntity)
}