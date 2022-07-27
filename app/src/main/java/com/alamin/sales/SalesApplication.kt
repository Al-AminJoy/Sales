package com.alamin.sales

import android.app.Application
import com.alamin.sales.di.AppComponent
import com.alamin.sales.di.DaggerAppComponent

class SalesApplication : Application() {
    lateinit var appComponent: AppComponent;
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}