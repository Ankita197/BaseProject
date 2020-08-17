package com.app.kujacustomerapp.ui.account.register

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.BaseResponse
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.request.account.SignUpRequest
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

open class RegisterViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : RegisterVariableViewModel(application) {

    val successLiveData = MutableLiveData<Event<Boolean>>()

    val errorLiveData = MutableLiveData<Event<String?>>()

    fun onForgotPasswordClicked() {
        var isValid = true
        nameError = if (name.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isNameValid(name!!)) {
                isValid = false
                R.string.enter_valid_mobile
            } else {
                null
            }
        }
        emailError = if (email.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isEmailValid(email!!)) {
                isValid = false
                R.string.enter_valid_mobile
            } else {
                null
            }
        }
        passwordError = if (password.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isPasswordValid(password!!)) {
                isValid = false
                R.string.enter_valid_mobile
            } else {
                null
            }
        }

        if (isValid) {
            callRegister()
        }
    }

    private fun callRegister() {
        showProgress = true
        accountRepository.callRegister(
            SignUpRequest(email, password, name),
            object :
                Enqueue<BaseResponse<UserData?>?> {
                override fun onSuccess(
                    call: Call<*>,
                    response: BaseResponse<UserData?>?
                ) {
                    showProgress = false
//                    successLiveData.postValue(
//                        Event(
//                            response?.status!!.isNotEmpty()
//                        )
//                    )
                }

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
                                t!!.localizedMessage
                            )
                        )
                    }
                }
            })
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isNameValid(name: String): Boolean {
        return name.length > 5
    }

    private fun isEmailValid(email: String): Boolean {
        return email.length==10
    }


}