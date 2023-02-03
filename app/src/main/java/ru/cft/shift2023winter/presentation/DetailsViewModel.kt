package ru.cft.shift2023winter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.use_case.GetBreweryByIdUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBreweryByIdUseCase: GetBreweryByIdUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState.Initial)
    val state: StateFlow<DetailsUiState> = _state

    fun loadData(breweryId: String) {
        viewModelScope.launch {
            _state.value = DetailsUiState.Loading

            try {
                val brewery = getBreweryByIdUseCase(breweryId)
                _state.value = DetailsUiState.Content(brewery)
            } catch (e: Exception) {
                _state.value = DetailsUiState.Error(e.message)
            }
        }
    }
}