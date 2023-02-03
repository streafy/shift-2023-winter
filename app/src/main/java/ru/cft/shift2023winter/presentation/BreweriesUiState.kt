package ru.cft.shift2023winter.presentation

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.Brewery

sealed interface BreweriesUiState {

    object Initial : BreweriesUiState

    object Loading : BreweriesUiState

    data class Content(val breweries: Flow<PagingData<Brewery>>) : BreweriesUiState

    data class Error(val message: String?) : BreweriesUiState
}