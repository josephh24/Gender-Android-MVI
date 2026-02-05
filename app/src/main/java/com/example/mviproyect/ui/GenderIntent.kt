package com.example.mviproyect.ui

sealed class GenderIntent {
    data class SearchGenderUser(val name: String): GenderIntent()
}