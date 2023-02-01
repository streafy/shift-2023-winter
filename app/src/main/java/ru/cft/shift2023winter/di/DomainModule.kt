package ru.cft.shift2023winter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2023winter.data.network.BreweriesApi
import ru.cft.shift2023winter.data.converter.BreweryConverter
import ru.cft.shift2023winter.data.repository.BreweryRepositoryImpl
import ru.cft.shift2023winter.domain.repository.BreweryRepository

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideBreweryRepository(breweriesApi: BreweriesApi, converter: BreweryConverter): BreweryRepository =
        BreweryRepositoryImpl(breweriesApi, converter)
}