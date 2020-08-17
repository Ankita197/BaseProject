package com.app.kujacustomerapp.ui.account.changepassword

import android.app.Application
import androidx.annotation.StringRes
import androidx.databinding.Bindable
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel

open class ChangePasswordVariableViewModel (application :Application): BaseObservableViewModel(application){

    var showProgress = false
        @Bindable get() = field
        set(showProgress) {
            field = showProgress
            notifyPropertyChanged(BR.showProgress)
        }

    var oldPassword: String? = null
    @Bindable get() =field
    set(oldPassword) {
        field=oldPassword
        notifyPropertyChanged(BR.oldPassword)
    }

    var newPassword: String? = null
        @Bindable get() = field
        set(newPassword) {
            field = newPassword
            notifyPropertyChanged(BR.newPassword)
        }
    var confirmPassword: String? = null
        @Bindable get() = field
        set(confirmPassword) {
            field = confirmPassword
            notifyPropertyChanged(BR.confirmPassword)
        }

    @StringRes
    var oldPasswordError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.oldPasswordError)
        }
    @StringRes
    var newPasswordError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.newPasswordError)
        }
    @StringRes
    var confirmPasswordError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.confirmPasswordError)
        }
}