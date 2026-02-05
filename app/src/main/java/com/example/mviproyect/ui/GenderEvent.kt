package com.example.mviproyect.ui

sealed class GenderEvent {
    data class ShowToast(val message: String): GenderEvent()
}