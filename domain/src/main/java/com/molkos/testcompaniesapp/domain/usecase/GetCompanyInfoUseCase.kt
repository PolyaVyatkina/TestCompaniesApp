package com.molkos.testcompaniesapp.domain.usecase

import com.molkos.testcompaniesapp.domain.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.repository.CompanyInfoRepository

class GetCompanyInfoUseCase(private val repository: CompanyInfoRepository) {

    suspend fun execute(id: Int): Response<CompanyInfo> {
        return repository.getCompanyInfo(id = id)
    }
}