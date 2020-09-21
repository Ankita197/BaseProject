package com.app.kujacustomerapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.kujacustomerapp.di.key.ViewModelKey
import com.app.kujacustomerapp.ui.account.SignUpViewModel
import com.app.kujacustomerapp.ui.account.changepassword.ChangePasswordViewModel
import com.app.kujacustomerapp.ui.account.forgotpassword.ForgotPasswordViewModel
import com.app.kujacustomerapp.ui.account.login.LoginViewModel
import com.app.kujacustomerapp.ui.account.register.RegisterViewModel
import com.app.kujacustomerapp.ui.base.ViewModelFactory
import com.app.kujacustomerapp.ui.dashboard.DashboardViewModel
import com.app.kujacustomerapp.ui.dashboard.rfid.RfidViewModel
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionViewModel
import com.app.kujacustomerapp.ui.dashboard.transfer_money.TransferMoneyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel?): ViewModel? //

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(RfidViewModel::class)
    abstract fun bindRfidViewModel(registerViewModel: RfidViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun bindForgotPasswordViewModel(forgotPasswordViewModel: ForgotPasswordViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindChangePasswordViewModel(loginViewModel: ChangePasswordViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindTransactionViewModel(transactionViewModel: TransactionViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(TransferMoneyViewModel::class)
    abstract fun bindTransferMoneyViewModel(transferMoneyViewModel: TransferMoneyViewModel): ViewModel?

}