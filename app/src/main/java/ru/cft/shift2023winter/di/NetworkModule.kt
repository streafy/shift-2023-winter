package ru.cft.shift2023winter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.cft.shift2023winter.data.serialization.LocalDateTimeDeserializer
import ru.cft.shift2023winter.data.api.BreweriesApi
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private companion object {
        private const val BREWERIES_BASE_URL = "https://api.openbrewerydb.org"
        private const val CONNECTION_TIMEOUT = 5L
        private const val WRITE_TIMEOUT = 5L
        private const val READ_TIMEOUT = 5L
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BREWERIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
            .create()

    @Provides
    fun provideBreweriesApi(retrofit: Retrofit): BreweriesApi =
        retrofit.create()
}