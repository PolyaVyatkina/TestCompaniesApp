package com.molkos.testcompaniesapp.app

import android.app.Application
import com.molkos.testcompaniesapp.data.di.dataModule
import com.molkos.testcompaniesapp.domain.di.domainModule
import com.molkos.testcompaniesapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}