package com.molkos.testcompaniesapp.data.repository

import com.molkos.testcompaniesapp.data.api.CompanyApi
import com.molkos.testcompaniesapp.data.mapper.toDomainModel
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.repository.CompanyInfoRepository

class CompanyInfoRepositoryImpl(private val api: CompanyApi): CompanyInfoRepository {

    override suspend fun getCompanyInfo(id: Int): Response<CompanyInfo> {
        return when (val companyInfo = api.fetchCompanyInfo(id = id)) {
            is Response.Error -> Response.Error(companyInfo.error)
            is Response.Success -> Response.Success(companyInfo.body.toDomainModel())
        }
    }
}