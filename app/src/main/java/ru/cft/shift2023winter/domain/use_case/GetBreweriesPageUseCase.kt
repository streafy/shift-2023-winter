package ru.cft.shift2023winter.domain.use_case

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import javax.inject.Inject

class GetBreweriesPageUseCase @Inject constructor(
    private val repository: BreweryRepository
) {
    operator fun invoke(pageNumber: Int): Flow<PagingData<Brewery>> =
        repository.getPage()
}