package com.molkos.testcompaniesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("img") val imageUrl: String
)
