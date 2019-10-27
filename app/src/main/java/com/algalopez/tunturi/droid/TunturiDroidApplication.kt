package com.algalopez.tunturi.droid


import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TunturiDroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TunturiDroidApplication)
            modules(listOf(dependencyModuleList))
        }
    }
}
