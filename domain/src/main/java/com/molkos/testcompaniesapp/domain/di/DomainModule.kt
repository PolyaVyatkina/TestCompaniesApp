package com.molkos.testcompaniesapp.domain.di

import com.molkos.testcompaniesapp.domain.usecase.GetCompaniesListUseCase
import com.molkos.testcompaniesapp.domain.usecase.GetCompanyInfoUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCompaniesListUseCase(repository = get())
    }

    factory {
        GetCompanyInfoUseCase(repository = get())
    }
}