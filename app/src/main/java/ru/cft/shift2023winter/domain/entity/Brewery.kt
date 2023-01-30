package ru.cft.shift2023winter.domain.entity

import java.time.LocalDateTime

data class Brewery(
    val id: String,
    val name: String,
    val breweryType: String,
    val street: String,
    val address2: String,
    val address3: String,
    val city: String,
    val state: String,
    val countryProvince: String,
    val postalCode: String,
    val country: String,
    val longitude: Double,
    val latitude: Double,
    val phone : String,
    val websiteUrl: String,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
)
