package ru.cft.shift2023winter.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.Brewery

interface BreweryRepository {

    suspend fun getList(): List<Brewery>

    fun getPage(): Flow<PagingData<Brewery>>

    suspend fun getById(id: String): Brewery
}