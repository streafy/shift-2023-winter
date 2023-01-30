package ru.cft.shift2023winter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2023winter.data.converter.BreweryConverter

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideBreweryConverter() : BreweryConverter =
        BreweryConverter()
}