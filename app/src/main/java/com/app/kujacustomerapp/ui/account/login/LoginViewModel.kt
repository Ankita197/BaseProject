package com.app.kujacustomerapp.ui.account.login

import android.app.Application
import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentLoginBinding
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.request.account.LoginRequest
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : LoginVariableViewModel(application) {

    var successLiveData = MutableLiveData<Event<Boolean?>>()
    private var binding: FragmentLoginBinding?=null
    var errorLiveData = MutableLiveData<Event<String?>>()

    fun setBinding( binding: FragmentLoginBinding){
        this.binding=binding
    }

    fun onLoginClicked() {
        var isValid = true
        emailError = if (mobile.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isEmailValid(mobile.toString())) {
                isValid = false
                R.string.enter_valid_mobile
            } else {
                null
            }
        }
        passwordError = if (password.isNullOrEmpty()) {
            isValid = false
            R.string.enter_password
        } else {
            if (!isPasswordValid(password!!)) {
                isValid = false
                R.string.enter_valid_password
            } else {
                null
            }
        }

        if (isValid) {
            callLogin()
        }
    }

    private fun callLogin() {

        //Uncomment this line to show loader
        showProgress = true
        //This is because we have no api
        accountRepository.callLogin(LoginRequest(mobile?.toLong(), password, 1),
            object : Enqueue<Boolean?> {
                override fun onSuccess(call: Call<*>, response: Boolean?) {
                    showProgress=false
                    successLiveData.postValue(
                        Event(
                            response
                        )
                    )
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
                                t.localizedMessage
                            )
                        )
                    }
                }
            })

    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isEmailValid(email: String): Boolean {
        return email.length==10
    }

    fun phoneValidator(editable: Editable) {
        if (binding?.edtPhone == null) return
        val minimumLength = 1
        if (!TextUtils.isEmpty(editable.toString()) && editable.length < minimumLength) {
            binding?.tilPhone?.error="Password must be minimum $minimumLength length"
        } else {
            binding?.tilPhone?.error=null
        }
    }

    fun passwordValidator(editable: Editable) {
        if (binding?.edtPassword == null) return
        val minimumLength = 1
        if (!TextUtils.isEmpty(editable.toString()) && editable.length < minimumLength) {
            binding?.tilPassword?.error="Password must be minimum $minimumLength length"
        } else {
            binding?.tilPassword?.error=null
        }
    }

}