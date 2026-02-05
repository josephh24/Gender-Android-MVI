package com.example.mviproyect.data.local.di

import com.example.mviproyect.data.local.repository.LocalDatabaseResource
import com.example.mviproyect.data.local.repository.LocalDatabaseResourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDatabaseModule {

    @Binds
    abstract fun provideLocalDatabase(impl: LocalDatabaseResourceImpl): LocalDatabaseResource
}