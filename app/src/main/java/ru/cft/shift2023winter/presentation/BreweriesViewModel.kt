package ru.cft.shift2023winter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.use_case.GetBreweriesPageUseCase
import javax.inject.Inject

@HiltViewModel
class BreweriesViewModel @Inject constructor(
    private val getBreweriesPageUseCase: GetBreweriesPageUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<BreweriesUiState> = MutableStateFlow(BreweriesUiState.Initial)
    val state: StateFlow<BreweriesUiState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = BreweriesUiState.Loading

            try {
                val breweries = getBreweriesPageUseCase().cachedIn(viewModelScope)
                _state.value = BreweriesUiState.Content(breweries)
            } catch (e: Exception) {
                _state.value = BreweriesUiState.Error(e.message)
            }
        }
    }
}