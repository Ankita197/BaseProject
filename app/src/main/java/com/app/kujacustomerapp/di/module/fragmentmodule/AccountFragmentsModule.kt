package com.app.kujacustomerapp.di.module.fragmentmodule

import com.app.kujacustomerapp.di.scope.FragmentScoped
import com.app.kujacustomerapp.ui.account.changepassword.ChangePasswordFragment
import com.app.kujacustomerapp.ui.account.forgotpassword.ForgotPasswordFragment
import com.app.kujacustomerapp.ui.account.forgotpassword.PasswordSendSuccessFragment
import com.app.kujacustomerapp.ui.account.login.LoginFragment
import com.app.kujacustomerapp.ui.account.register.RegisterFragment
import com.app.kujacustomerapp.ui.account.register.SequrityQuestionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AccountFragmentsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributePasswordSendSuccessFragment(): PasswordSendSuccessFragment



    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSecurityQuestionFragment(): SequrityQuestionFragment

}