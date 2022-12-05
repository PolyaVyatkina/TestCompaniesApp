package com.molkos.testcompaniesapp.domain.repository

import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.domain.model.Response

interface CompaniesListRepository {

    suspend fun getCompaniesList(): Response<List<Company>>
}