package ru.cft.shift2023winter.presentation

import ru.cft.shift2023winter.domain.entity.Brewery

sealed interface DetailsUiState {

    object Initial : DetailsUiState

    object Loading : DetailsUiState

    data class Content(val brewery: Brewery) : DetailsUiState

    data class Error(val message: String?) : DetailsUiState
}