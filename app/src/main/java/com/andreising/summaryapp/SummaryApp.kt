package com.andreising.summaryapp

import android.app.Application
import com.andreising.summaryapp.di.appModule
import org.koin.core.context.startKoin

class SummaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}