package ru.cft.shift2023winter.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.cft.shift2023winter.data.converter.BreweryConverter
import ru.cft.shift2023winter.domain.entity.Brewery
import javax.inject.Inject

class BreweriesPagingSource @Inject constructor(
    private val breweriesApi: BreweriesApi,
    private val converter: BreweryConverter
) : PagingSource<Int, Brewery>() {

    companion object {

        const val INITIAL_PAGE_NUMBER = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Brewery>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Brewery> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = breweriesApi.getPage(pageNumber)
                .map { brewery -> converter.convert(brewery) }

            LoadResult.Page(
                data = response,
                prevKey = if (pageNumber > 1) pageNumber - 1 else null,
                nextKey = if (response.isEmpty()) null else pageNumber + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}