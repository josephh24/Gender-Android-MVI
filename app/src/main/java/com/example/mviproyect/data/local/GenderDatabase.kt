package com.example.mviproyect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mviproyect.data.local.dao.GenderUserDao
import com.example.mviproyect.data.local.entity.GenderUserEntity

@Database(
    entities = [
        GenderUserEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class GenderDatabase: RoomDatabase() {

    abstract fun genderUserDao(): GenderUserDao
}