package com.molkos.testcompaniesapp.di

import com.molkos.testcompaniesapp.presentation.info.InfoViewModel
import com.molkos.testcompaniesapp.presentation.main.MainViewModel
import org.koin.dsl.module

val appModule = module {

    single {
        MainViewModel(getCompaniesListUseCase = get())
    }

    single {
        InfoViewModel(getCompanyInfoUseCase = get())
    }

}