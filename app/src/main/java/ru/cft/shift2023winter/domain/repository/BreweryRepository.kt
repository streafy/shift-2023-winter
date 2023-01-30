package ru.cft.shift2023winter.domain.repository

import ru.cft.shift2023winter.domain.entity.Brewery

interface BreweryRepository {

    suspend fun getList(): List<Brewery>

    suspend fun getById(id: String): Brewery
}