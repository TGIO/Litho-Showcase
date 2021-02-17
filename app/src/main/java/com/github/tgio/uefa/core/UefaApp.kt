package com.github.tgio.uefa.core

import android.app.Application
import com.facebook.soloader.SoLoader
import com.github.tgio.uefa.di.dataModule
import com.github.tgio.uefa.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class UefaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
//        ComponentsConfiguration.debugHighlightInteractiveBounds = true
//        ComponentsConfiguration.debugHighlightMountBounds = true

        startKoin {
            androidContext(this@UefaApp)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    dataModule,
                    viewModelModule
                )
            )
        }
    }
}
