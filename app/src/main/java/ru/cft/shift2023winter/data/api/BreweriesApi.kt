package ru.cft.shift2023winter.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.cft.shift2023winter.data.model.BreweryModel

interface BreweriesApi {

    @GET("/breweries")
    suspend fun getList(): List<BreweryModel>

    @GET("/breweries/{id}")
    suspend fun getById(@Path("id") breweryId: String): BreweryModel
}