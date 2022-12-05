package com.molkos.testcompaniesapp.data.mapper

import com.molkos.testcompaniesapp.data.model.Company

fun Company.toDomainModel(): com.molkos.testcompaniesapp.domain.model.Company {
    return com.molkos.testcompaniesapp.domain.model.Company(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}