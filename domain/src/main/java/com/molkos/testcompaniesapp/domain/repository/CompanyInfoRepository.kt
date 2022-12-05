package com.molkos.testcompaniesapp.domain.repository

import com.molkos.testcompaniesapp.domain.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.model.Response

interface CompanyInfoRepository {

    suspend fun getCompanyInfo(id: Int): Response<CompanyInfo>
}