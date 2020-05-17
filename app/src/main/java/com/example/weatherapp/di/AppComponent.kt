package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RetrofitModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(activity: MainActivity)



}