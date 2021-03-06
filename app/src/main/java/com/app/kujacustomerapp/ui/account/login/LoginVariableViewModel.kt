package com.app.kujacustomerapp.ui.account.login

import android.app.Application
import androidx.annotation.StringRes
import androidx.databinding.Bindable
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel

open class LoginVariableViewModel(application: Application) :
    BaseObservableViewModel(application) {

    var showProgress = false
        @Bindable get() = field
        set(showProgress) {
            field = showProgress
            notifyPropertyChanged(BR.showProgress)
        }

    var mobile: String? = null
        @Bindable get() = field
        set(mobile) {
            field = mobile
            notifyPropertyChanged(BR.email)
        }

    @StringRes
    var emailError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.emailError)
        }

    var password: String? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }

    @StringRes
    var passwordError: Int? = null
        @Bindable get() = field
        set(email) {
            field = email
            notifyPropertyChanged(BR.passwordError)
        }

}