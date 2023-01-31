package ru.cft.shift2023winter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.use_case.GetBreweryListUseCase
import javax.inject.Inject

@HiltViewModel
class BreweriesViewModel @Inject constructor(
    private val getBreweryListUseCase: GetBreweryListUseCase
) : ViewModel() {

    private val _state: MutableLiveData<BreweriesUiState> = MutableLiveData(BreweriesUiState.Initial)
    val state: LiveData<BreweriesUiState> = _state

    fun loadDate() {
        viewModelScope.launch {
            _state.value = BreweriesUiState.Loading

            try {
                val breweries = getBreweryListUseCase()
                _state.value = BreweriesUiState.Content(breweries)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = BreweriesUiState.Error(e.message)
            }
        }
    }
}