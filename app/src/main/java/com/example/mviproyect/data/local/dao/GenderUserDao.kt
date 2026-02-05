package com.example.mviproyect.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mviproyect.data.local.entity.GenderUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenderUserDao {
    @Query("SELECT * FROM genderUser WHERE name = :name")
    fun getGenderUser(name: String): Flow<GenderUserEntity>

    @Upsert
    fun saveGenderUser(genderUserEntity: GenderUserEntity)
}