package com.molkos.testcompaniesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyInfo(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("img") val imageUrl: String,
    @SerialName("description") val description: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("www") val website: String,
    @SerialName("phone") val phone: String,
)
