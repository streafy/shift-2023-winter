package ru.cft.shift2023winter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.cft.shift2023winter.domain.use_case.GetBreweriesPageUseCase
import javax.inject.Inject

@HiltViewModel
class BreweriesViewModel @Inject constructor(
    private val getBreweriesPageUseCase: GetBreweriesPageUseCase
) : ViewModel() {

    fun getPage() =
        getBreweriesPageUseCase().cachedIn(viewModelScope)
}