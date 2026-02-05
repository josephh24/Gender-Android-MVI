package com.example.mviproyect.domain.repository

import com.example.mviproyect.domain.model.GenderUser
import kotlinx.coroutines.flow.Flow

interface GenderUserRepository {
    fun getGenderUser(name: String): Flow<GenderUser>
}