package com.coderbot.quotes.application

import android.app.Application
import com.coderbot.quotes.koin.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application()
{
    override fun onCreate()
    {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.INFO)
            modules(koinModule)
        }
    }
}