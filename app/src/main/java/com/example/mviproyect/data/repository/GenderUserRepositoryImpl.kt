package com.example.mviproyect.data.repository

import com.example.mviproyect.data.local.entity.asExternalModel
import com.example.mviproyect.data.local.repository.LocalDatabaseResource
import com.example.mviproyect.data.network.model.asEntity
import com.example.mviproyect.data.network.repository.NetworkDataSource
import com.example.mviproyect.domain.model.GenderUser
import com.example.mviproyect.domain.repository.GenderUserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class GenderUserRepositoryImpl @Inject constructor(
    private val localDatabaseResource: LocalDatabaseResource,
    private val networkDataSource: NetworkDataSource
): GenderUserRepository {
    override fun getGenderUser(name: String): Flow<GenderUser> {
        return localDatabaseResource
            .getGenderUserEntity(name)
            .map { genderUserEntity ->
                delay(200)
                genderUserEntity.asExternalModel() //from DB
            }
            .retryWhen { cause, attempt ->
                if (cause is NullPointerException && attempt < 1) {
                    fetchGenderUser(name) // network
                    true
                } else {
                    false
                }
            }
            .take(1)
    }

    private suspend fun fetchGenderUser(name: String): GenderUser {
        return networkDataSource.fetchNetworkGenderUser(name) // network
            .asEntity() // obj to DB format
            .also { localDatabaseResource.saveGenderUserEntity(it) } //save in DB
            .asExternalModel() // obj to domain format
    }
}