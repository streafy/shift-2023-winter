package ru.cft.shift2023winter.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class BreweryModel(
    val id: String,
    val name: String,
    @SerializedName("brewery_type")
    val breweryType: String,
    val street: String,
    @SerializedName("address_2")
    val address2: String,
    @SerializedName("address_3")
    val address3: String,
    val city: String,
    val state: String,
    @SerializedName("county_province")
    val countryProvince: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val country: String,
    val longitude: Double,
    val latitude: Double,
    val phone : String,
    @SerializedName("website_url")
    val websiteUrl: String,
    @SerializedName("updated_at")
    val updatedAt: LocalDateTime,
    @SerializedName("created_at")
    val createdAt: LocalDateTime
)