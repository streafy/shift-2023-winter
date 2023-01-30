package ru.cft.shift2023winter.data.converter

import ru.cft.shift2023winter.data.model.BreweryModel
import ru.cft.shift2023winter.domain.entity.Brewery
import javax.inject.Inject

class BreweryConverter @Inject constructor() {

    fun convert(from: BreweryModel): Brewery =
        Brewery(
            id = from.id,
            name = from.name,
            breweryType = from.breweryType,
            street = from.street,
            city = from.city,
            state = from.state,
            countryProvince = from.countryProvince,
            postalCode = from.postalCode,
            country = from.country,
            longitude = from.longitude,
            latitude = from.latitude,
            phone = from.phone,
            websiteUrl = from.websiteUrl,
            updatedAt = from.updatedAt,
            createdAt = from.createdAt
        )
}