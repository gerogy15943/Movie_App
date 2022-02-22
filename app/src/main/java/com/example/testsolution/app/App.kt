package com.example.testsolution.app

import android.app.Application
import com.example.testsolution.di.AppComponent
import com.example.testsolution.di.AppModule
import com.example.testsolution.di.DaggerAppComponent
import dagger.Component

class App: Application() {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}