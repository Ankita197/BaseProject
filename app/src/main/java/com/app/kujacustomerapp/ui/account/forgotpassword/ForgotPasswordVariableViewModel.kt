package com.app.kujacustomerapp.ui.account.forgotpassword

import android.app.Application
import androidx.annotation.StringRes
import androidx.databinding.Bindable
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel

open class ForgotPasswordVariableViewModel(application: Application) :
    BaseObservableViewModel(application) {

    var showProgress = false
        @Bindable get() = field
        set(showProgress) {
            field = showProgress
            notifyPropertyChanged(BR.showProgress)
        }

    var phone: String? = null
        @Bindable get() = field
        set(email) {
            field = email
            notifyPropertyChanged(BR.email)
        }

    @StringRes
    var phoneError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.emailError)
        }

}