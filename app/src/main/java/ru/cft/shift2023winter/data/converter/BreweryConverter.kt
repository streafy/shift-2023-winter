package ru.cft.shift2023winter.data.converter

import ru.cft.shift2023winter.data.model.BreweryModel
import ru.cft.shift2023winter.domain.entity.Brewery

class BreweryConverter {

    fun convert(from: BreweryModel): Brewery =
        Brewery(
            id = from.id,
            name = from.name,
            breweryType = from.breweryType,
            street = from.street,
            address2 = from.address2,
            address3 = from.address3,
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