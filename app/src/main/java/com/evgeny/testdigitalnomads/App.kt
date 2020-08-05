package com.evgeny.testdigitalnomads

import android.app.Application
import android.content.Context
import com.evgeny.testdigitalnomads.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    companion object {
        lateinit var appCtx: Context
            private set

    }

    //==============================================================================================

    override fun onCreate() {
        super.onCreate()

        initAppContext()
        initStetho()
        initKoin()
    }

    //==============================================================================================

    private fun initAppContext() {
        appCtx = applicationContext
    }

    private fun initStetho() {
        // оладка с помощью Chrome

        Stetho.initializeWithDefaults(applicationContext)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(NetworkModule)
            modules(StorageModule)
            modules(RepositoryModule)
            modules(VMModule)
            modules(OtherModule)
        }
    }


}