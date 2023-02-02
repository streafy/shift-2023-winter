package ru.cft.shift2023winter.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.shift2023winter.data.model.BreweryModel

interface BreweriesApi {

    @GET("/breweries")
    suspend fun getList(): List<BreweryModel>

    @GET("/breweries")
    suspend fun getPage(@Query("page") pageNumber: Int): List<BreweryModel>

    @GET("/breweries/{id}")
    suspend fun getById(@Path("id") breweryId: String): BreweryModel
}