package com.app.kujacustomerapp.ui.account.register

import android.app.Application
import androidx.annotation.StringRes
import androidx.databinding.Bindable
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel

open class RegisterVariableViewModel(application: Application) :
    BaseObservableViewModel(application) {

    var showProgress = false
        @Bindable get() = field
        set(showProgress) {
            field = showProgress
            notifyPropertyChanged(BR.showProgress)
        }

    var name: String? = null
        @Bindable get() = field
        set(email) {
            field = email
            notifyPropertyChanged(BR.name)
        }

    @StringRes
    var nameError: Int? = null
        @Bindable get() = field
        set(password) {
            field = password
            notifyPropertyChanged(BR.nameError)
        }

    var email: String? = null
        @Bindable get() = field
        set(email) {
            field = email
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

    var dateOfBirth: String? = null
        @Bindable get() = field
        set(dateOfBirth) {
            field = dateOfBirth
            notifyPropertyChanged(BR.dateOfBirth)
        }

    @StringRes
    var dateOfBirthError: Int? = null
        @Bindable get() = field
        set(dateOfBirthError) {
            field = dateOfBirthError
            notifyPropertyChanged(BR.dateOfBirthError)
        }

    var phone: String? = null
        @Bindable get() = field
        set(phone) {
            field = phone
            notifyPropertyChanged(BR.phone)
        }

    @StringRes
    var phoneError: Int? = null
        @Bindable get() = field
        set(phoneError) {
            field = phoneError
            notifyPropertyChanged(BR.phoneError)
        }
    var postalCode: String? = null
        @Bindable get() = field
        set(postalCode) {
            field = postalCode
            notifyPropertyChanged(BR.postalCode)
        }

    @StringRes
    var postalCodeError: Int? = null
        @Bindable get() = field
        set(postalCodeError) {
            field = postalCodeError
            notifyPropertyChanged(BR.postalCodeError)
        }
    var nationalId: String? = null
        @Bindable get() = field
        set(nationalId) {
            field = nationalId
            notifyPropertyChanged(BR.nationalId)
        }
    @StringRes
    var nationalIdError: Int? = null
        @Bindable get() = field
        set(nationalIdError) {
            field = nationalIdError
            notifyPropertyChanged(BR.nationalIdError)
        }
    @StringRes
    var passwordError: Int? = null
        @Bindable get() = field
        set(email) {
            field = email
            notifyPropertyChanged(BR.passwordError)
        }

}