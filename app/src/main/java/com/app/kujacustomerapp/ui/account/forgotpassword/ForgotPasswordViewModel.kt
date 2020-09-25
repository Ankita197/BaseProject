package com.app.kujacustomerapp.ui.account.forgotpassword

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.request.account.ForgotPasswordRequest
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : ForgotPasswordVariableViewModel(application) {

    val successLiveData = MutableLiveData<Event<Boolean?>>()

    val errorLiveData = MutableLiveData<Event<String?>>()

    fun onForgotPasswordClicked() {
        var isValid = true
        val phone = phone?.trim()
        phoneError =
            if (phone.isNullOrBlank()) {
                isValid = false
                R.string.enter_mobile
            } else {
                if (!isEmailValid(phone)) {
                    isValid = false
                    R.string.enter_valid_mobile
                } else {
                    null
                }
            }

        if (isValid) {
            callForgotPassword()
        }

    }

    private fun callForgotPassword() {
        showProgress = true
        accountRepository.callForgotPassword(
            ForgotPasswordRequest(1,phone),
            object :
                Enqueue<Boolean?> {
                              override fun onError(call: Call<*>, t: Throwable) {
                    showProgress = false
                    if (t is AppHttpException) {
                        errorLiveData.postValue(
                            Event(
                                t.errorResponse.message
                            )
                        )
                    } else {
                        errorLiveData.postValue(
                            Event(
                                t.localizedMessage
                            )
                        )
                    }
                }

                override fun onSuccess(call: Call<*>, response: Boolean?) {
                    showProgress=false
                    successLiveData.postValue(Event(response))
                }
            })
    }

    private fun isEmailValid(phone: String): Boolean {
        return phone.length==10
    }


}