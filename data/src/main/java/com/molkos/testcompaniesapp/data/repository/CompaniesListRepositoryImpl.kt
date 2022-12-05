package com.molkos.testcompaniesapp.data.repository

import com.molkos.testcompaniesapp.data.api.CompanyApi
import com.molkos.testcompaniesapp.data.mapper.toDomainModel
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.domain.repository.CompaniesListRepository

class CompaniesListRepositoryImpl(private val api: CompanyApi) : CompaniesListRepository {

    override suspend fun getCompaniesList(): Response<List<Company>> {
        return when (val companies = api.fetchCompaniesList()) {
            is Response.Error -> Response.Error(companies.error)
            is Response.Success -> Response.Success(companies.body.map { it.toDomainModel() })
        }
    }
}