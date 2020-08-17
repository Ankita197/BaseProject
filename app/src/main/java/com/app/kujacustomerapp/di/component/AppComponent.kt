package com.app.kujacustomerapp.di.component

import android.content.Context
import com.app.kujacustomerapp.KujaCustomerApp
import com.app.kujacustomerapp.di.module.ActivityModule
import com.app.kujacustomerapp.di.module.ApplicationModule
import com.app.kujacustomerapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<KujaCustomerApp?> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context?): AppComponent?
    }
}