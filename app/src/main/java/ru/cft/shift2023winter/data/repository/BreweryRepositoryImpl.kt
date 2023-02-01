package ru.cft.shift2023winter.data.repository

import ru.cft.shift2023winter.data.network.BreweriesApi
import ru.cft.shift2023winter.data.converter.BreweryConverter
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import javax.inject.Inject

class BreweryRepositoryImpl @Inject constructor(
    private val breweriesApi: BreweriesApi,
    private val converter: BreweryConverter
) : BreweryRepository {

    override suspend fun getList(): List<Brewery> =
        breweriesApi.getList()
            .map { brewery -> converter.convert(brewery) }

    override suspend fun getById(id: String): Brewery =
        converter.convert(breweriesApi.getById(id))
}