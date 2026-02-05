package com.example.mviproyect.ui

import com.example.mviproyect.domain.model.GenderUser

data class GenderUiState(
    val genderUser: GenderUser? = null,
    val isLoading: Boolean = false
)