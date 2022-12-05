package com.molkos.testcompaniesapp.domain.usecase

import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.repository.CompaniesListRepository

class GetCompaniesListUseCase(private val repository: CompaniesListRepository) {

    suspend fun execute(): Response<List<Company>> {
        return repository.getCompaniesList()
    }
}