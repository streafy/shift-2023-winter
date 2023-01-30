package ru.cft.shift2023winter.data.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift2023winter.data.LocalDateTimeAdapter
import ru.cft.shift2023winter.data.api.BreweriesApi
import ru.cft.shift2023winter.data.converter.BreweryConverter
import ru.cft.shift2023winter.domain.entity.Brewery
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class BreweryRepositoryImpl : BreweryRepository {

    private companion object {

        const val BASE_URL = "https://api.openbrewerydb.org"
        const val CONNECTION_TIMEOUT = 5L
        const val WRITE_TIMEOUT = 5L
        const val READ_TIMEOUT = 5L
    }

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private val loadApi by lazy {
        retrofit.create(BreweriesApi::class.java)
    }

    private val converter = BreweryConverter()

    override suspend fun getList(): List<Brewery> =
        loadApi.getList()
            .map { brewery -> converter.convert(brewery) }

    override suspend fun getById(id: String): Brewery =
        converter.convert(loadApi.getById(id))
}