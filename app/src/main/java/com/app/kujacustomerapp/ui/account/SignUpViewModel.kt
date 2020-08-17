package com.app.kujacustomerapp.ui.account

import android.app.Application
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : BaseObservableViewModel(application) {

}