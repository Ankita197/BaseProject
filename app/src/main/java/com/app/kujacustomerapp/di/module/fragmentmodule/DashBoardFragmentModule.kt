package com.app.kujacustomerapp.di.module.fragmentmodule

import com.app.kujacustomerapp.di.scope.FragmentScoped
import com.app.kujacustomerapp.ui.dashboard.DashboardFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashBoardFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

}
