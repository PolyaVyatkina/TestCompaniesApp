package com.molkos.testcompaniesapp.data.di

import com.molkos.testcompaniesapp.data.api.CompanyApi
import com.molkos.testcompaniesapp.data.api.CompanyApiRef
import com.molkos.testcompaniesapp.data.client.KtorClient
import com.molkos.testcompaniesapp.data.repository.CompaniesListRepositoryImpl
import com.molkos.testcompaniesapp.data.repository.CompanyInfoRepositoryImpl
import com.molkos.testcompaniesapp.domain.repository.CompaniesListRepository
import com.molkos.testcompaniesapp.domain.repository.CompanyInfoRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule  = module {

    single {
        KtorClient.client
    }

    single {
        CompanyApiRef(client = get())
    } bind CompanyApi::class

    single {
        CompaniesListRepositoryImpl(api = get())
    } bind CompaniesListRepository::class

    single {
        CompanyInfoRepositoryImpl(api = get())
    } bind CompanyInfoRepository::class
}