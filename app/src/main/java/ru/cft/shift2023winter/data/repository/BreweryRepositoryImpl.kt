package ru.cft.shift2023winter.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.data.converter.BreweryConverter
import ru.cft.shift2023winter.data.network.BreweriesApi
import ru.cft.shift2023winter.data.network.BreweriesPagingSource
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import javax.inject.Inject

class BreweryRepositoryImpl @Inject constructor(
    private val breweriesApi: BreweriesApi,
    private val converter: BreweryConverter,
) : BreweryRepository {

    companion object {

        const val DEFAULT_PAGE_SIZE = 20
    }

    override suspend fun getList(): List<Brewery> =
        breweriesApi.getList()
            .map { brewery -> converter.convert(brewery) }

    override fun getPage(): Flow<PagingData<Brewery>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreweriesPagingSource(breweriesApi, converter) }
        ).flow

    override suspend fun getById(id: String): Brewery =
        converter.convert(breweriesApi.getById(id))
}