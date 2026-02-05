package com.example.mviproyect.data.local.di

import com.example.mviproyect.data.local.GenderDatabase
import com.example.mviproyect.data.local.dao.GenderUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun bindGenderDao(db: GenderDatabase): GenderUserDao = db.genderUserDao()
}