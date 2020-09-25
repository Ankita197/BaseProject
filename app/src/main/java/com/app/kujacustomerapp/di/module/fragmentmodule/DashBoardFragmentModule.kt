package com.app.kujacustomerapp.di.module.fragmentmodule

import com.app.kujacustomerapp.di.scope.FragmentScoped
import com.app.kujacustomerapp.ui.account.changepassword.ChangePasswordFragment
import com.app.kujacustomerapp.ui.account.register.SequrityQuestionFragment
import com.app.kujacustomerapp.ui.dashboard.DashboardFragment
import com.app.kujacustomerapp.ui.dashboard.rfid.RfidFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionHistoryFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.DailyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.MonthlyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.WeeklyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.YearlyFragment
import com.app.kujacustomerapp.ui.dashboard.transfer_money.TransferMoneyFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashBoardFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeTransactionHistoryFragment(): TransactionHistoryFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeYearlyFragment(): YearlyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDailyFragment(): DailyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeWeeklyFragment(): WeeklyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMonthlyFragment(): MonthlyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeRfidFragment(): RfidFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeTransferMoneyFragment(): TransferMoneyFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeChangePasswordFragment(): ChangePasswordFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSecurityQuestionFragment(): SequrityQuestionFragment

}
