package com.app.kujacustomerapp.ui.dashboard.transfer_money

import android.app.Application
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentTransferMoneyBinding
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.remote.entity.request.account.MakePaymentRequest
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import com.app.kujacustomerapp.remote.entity.response.dashboard.MakePaymentResponse
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.repository.dashboard.DashboardRepository
import com.app.kujacustomerapp.ui.base.event.Event
import com.google.gson.Gson
import retrofit2.Call
import javax.inject.Inject

class TransferMoneyViewModel @Inject constructor(
    application: Application, private val dashboardRepository: DashboardRepository
    , private val accountSharedPrefs: AccountSharedPrefs
) :
    TransferMoneyVariableViewModel(application) {

    private var binding: FragmentTransferMoneyBinding? = null
    var successLiveData = MutableLiveData<Event<MakePaymentResponse?>>()
    fun onValidate(): Boolean?{
        var isValid = true
        receiverPhoneNoError = if (receiverPhoneNo.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            null
        }
        amountError = if (amount.isNullOrEmpty()) {
            isValid = false
            (R.string.enter_amount)
        } else {
            null
        }
        return isValid

    }

    fun makePayment() {

        val gson = Gson()
        var userData: UserData = gson.fromJson(accountSharedPrefs.userData, UserData::class.java)
        dashboardRepository.makePayment(
            MakePaymentRequest(
                userData.user?.mobileNumber,
                amount,
                receiverPhoneNo,
                narration
            ), object : Enqueue<MakePaymentResponse> {
                override fun onSuccess(call: Call<*>, response: MakePaymentResponse) {

                    successLiveData.postValue(Event(response))
                }

                override fun onError(call: Call<*>, t: Throwable) {
                }
            })
    }

    fun phoneValidator(editable: Editable) {
        if (binding?.edtPhone == null) return
        val minimumLength = 1
        if (!TextUtils.isEmpty(editable.toString()) && editable.length < minimumLength) {
            binding?.tilPhone?.error = "Password must be minimum $minimumLength length"
        } else {
            binding?.tilPhone?.error = null
        }
    }

    fun amountValidator(editable: Editable) {
        if (binding?.edtAmount == null) return
        val minimumLength = 1
        if (!TextUtils.isEmpty(editable.toString()) && editable.length < minimumLength) {
            binding?.tilAmount?.error = "Password must be minimum $minimumLength length"
        } else {
            binding?.tilAmount?.error = null
        }
    }

    fun setBinding(binding: FragmentTransferMoneyBinding) {
        this.binding = binding
    }
}