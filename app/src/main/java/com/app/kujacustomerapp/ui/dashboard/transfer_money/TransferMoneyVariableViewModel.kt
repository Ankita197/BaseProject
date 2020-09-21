package com.app.kujacustomerapp.ui.dashboard.transfer_money

import android.app.Application
import androidx.databinding.Bindable
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.ui.base.BaseObservableViewModel

open class TransferMoneyVariableViewModel(application: Application) :
    BaseObservableViewModel(application) {

    var showProgress = false
        @Bindable get() = field
        set(showProgress) {
            field = showProgress
            notifyPropertyChanged(BR.showProgress)
        }

    var receiverPhoneNo: String? = null
        @Bindable get() = field
        set(receiverPhoneNo) {
            field = receiverPhoneNo
            notifyPropertyChanged(BR.receiverPhoneNo)
        }

    var amount: String? = null
        @Bindable get() = field
        set(amount) {
            field = amount
            notifyPropertyChanged(BR.amount)
        }

    var narration: String? = null
        @Bindable get() = field
        set(narration) {
            field = narration
            notifyPropertyChanged(BR.narration)
        }

    var receiverPhoneNoError: Int? = null
        @Bindable get() = field
        set(receiverPhoneNoError) {
            field = receiverPhoneNoError
            notifyPropertyChanged(BR.receiverPhoneNoError)
        }

    var amountError: Int? = null
        @Bindable get() = field
        set(amountError) {
            field = amountError
            notifyPropertyChanged(BR.amountError)
        }
}