package ru.cft.shift2023winter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.shift2023winter.domain.repository.BreweryRepository
import ru.cft.shift2023winter.domain.use_case.GetBreweryListUseCase

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideGetBreweryListUseCase(breweryRepository: BreweryRepository) =
        GetBreweryListUseCase(breweryRepository)
}