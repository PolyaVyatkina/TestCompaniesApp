package com.molkos.testcompaniesapp.data.api

import com.molkos.testcompaniesapp.data.model.Company
import com.molkos.testcompaniesapp.data.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.model.Response

interface CompanyApi {

    suspend fun fetchCompaniesList(): Response<List<Company>>

    suspend fun fetchCompanyInfo(id: Int): Response<CompanyInfo>
}