package com.nikita.kut.android.a18_permissionsanddate

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class LocationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}