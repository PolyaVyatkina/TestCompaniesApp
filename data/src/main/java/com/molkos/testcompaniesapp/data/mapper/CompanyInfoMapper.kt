package com.molkos.testcompaniesapp.data.mapper

import com.molkos.testcompaniesapp.data.model.CompanyInfo

fun CompanyInfo.toDomainModel(): com.molkos.testcompaniesapp.domain.model.CompanyInfo {
    return com.molkos.testcompaniesapp.domain.model.CompanyInfo(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        latitude = latitude,
        longitude = longitude,
        website = website,
        phone = phone
    )
}