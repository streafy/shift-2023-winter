package ru.cft.shift2023winter.presentation

import ru.cft.shift2023winter.domain.entity.Brewery

sealed interface BreweriesUiState {

    object Initial : BreweriesUiState

    object Loading : BreweriesUiState

    data class Content(val breweries: List<Brewery>) : BreweriesUiState

    data class Error(val message: String?) : BreweriesUiState
}