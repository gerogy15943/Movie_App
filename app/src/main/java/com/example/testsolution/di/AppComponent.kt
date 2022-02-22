package com.example.testsolution.di

import com.example.testsolution.presentation.fragments.FragmentList
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class, ViewModelModule::class, UseCase::class])
interface AppComponent {
    fun inject(fragmentList: FragmentList)
}