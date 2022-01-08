package com.assemblermaticstudio.ioasysclient

import android.app.Application
import com.assemblermaticstudio.ioasysclient.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // inicializar Koin
        startKoin { androidContext(this@App) }
        Modules.load()
    }
}