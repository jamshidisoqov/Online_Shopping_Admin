package uz.gita.online_shopping_admin

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Created by Jamshid Isoqov an 10/9/2022
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        instance = this
    }
}