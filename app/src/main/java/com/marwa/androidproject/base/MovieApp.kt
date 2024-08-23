package com.marwa.androidproject.base

import android.app.Application
import com.marwa.androidproject.di.datasourceModule
import com.marwa.androidproject.di.networkModule
import com.marwa.androidproject.di.repositoryModule
import com.marwa.androidproject.di.useCaseModule
import com.marwa.androidproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }

    private fun startKoin(app: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(
                networkModule,
                datasourceModule,
                viewModelModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}