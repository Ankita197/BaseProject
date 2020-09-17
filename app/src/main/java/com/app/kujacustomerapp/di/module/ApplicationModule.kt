package com.app.kujacustomerapp.di.module

import android.app.Application
import android.content.Context
import com.app.kujacustomerapp.KujaCustomerApp
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.persistance.DashboardSharedPrefs
import com.app.kujacustomerapp.remote.base.interceptor.NetworkErrorInterceptor
import com.app.kujacustomerapp.repository.account.AccountDataRepository
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.repository.dashboard.DashboardDataRepository
import com.app.kujacustomerapp.repository.dashboard.DashboardRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return KujaCustomerApp.instance
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return KujaCustomerApp.instance
    }

    @Singleton
    @Provides
    fun provideAccountDataRepository(accountDataRepository: AccountDataRepository): AccountRepository {
        return accountDataRepository
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(dashboardDataRepository: DashboardDataRepository): DashboardRepository {
        return dashboardDataRepository
    }

    @Provides
    @Singleton
    fun provideAccountSharedPrefs(): AccountSharedPrefs {
        return AccountSharedPrefs(KujaCustomerApp.instance)
    }

    @Provides
    @Singleton
    fun provideDashboardSharedPrefs(): DashboardSharedPrefs {
        return DashboardSharedPrefs(KujaCustomerApp.instance)
    }

    @Provides
    @Singleton
    fun provideNetworkErrorInterceptor(
        gson: Gson
    ): NetworkErrorInterceptor {
        return NetworkErrorInterceptor(
            KujaCustomerApp.instance,
            KujaCustomerApp.instance.resources.getString(
                R.string.no_internet_connection
            ), gson
        )
    }
}