package ru.cft.shift2023winter.domain.use_case

import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import javax.inject.Inject

class GetBreweryListUseCase @Inject constructor(
    private val repository: BreweryRepository
) {

    suspend operator fun invoke(): List<Brewery> =
        repository.getList()
}