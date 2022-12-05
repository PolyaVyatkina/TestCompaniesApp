package com.molkos.testcompaniesapp.domain.model

data class CompanyInfo(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val website: String,
    val phone: String,
)
