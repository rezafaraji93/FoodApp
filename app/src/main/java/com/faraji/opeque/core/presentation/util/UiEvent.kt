package com.faraji.opeque.core.presentation.util


sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
}