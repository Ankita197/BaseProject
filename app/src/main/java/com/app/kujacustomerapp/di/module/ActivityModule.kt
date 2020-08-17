package com.app.kujacustomerapp.di.module

import com.app.kujacustomerapp.SplashActivity
import com.app.kujacustomerapp.di.module.fragmentmodule.AccountFragmentsModule
import com.app.kujacustomerapp.di.module.fragmentmodule.DashBoardFragmentModule
import com.app.kujacustomerapp.di.scope.ActivityScoped
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity?

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AccountFragmentsModule::class])
    abstract fun contributeAccountActivity(): AccountActivity?

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DashBoardFragmentModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity?

}