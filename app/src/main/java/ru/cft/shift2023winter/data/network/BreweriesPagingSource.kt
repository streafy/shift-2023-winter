package ru.cft.shift2023winter.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.cft.shift2023winter.data.model.BreweryModel
import javax.inject.Inject

class BreweriesPagingSource @Inject constructor(
    private val breweriesApi: BreweriesApi
) : PagingSource<Int, BreweryModel>() {

    companion object {

        const val INITIAL_PAGE_NUMBER = 1
    }

    override fun getRefreshKey(state: PagingState<Int, BreweryModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreweryModel> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = breweriesApi.getPage(pageNumber)
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            val nextPageNumber = if (response.isEmpty()) null else pageNumber + 1
            LoadResult.Page(response, prevPageNumber, nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}