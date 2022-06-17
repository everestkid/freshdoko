package com.teamonetech.freshdoko

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}