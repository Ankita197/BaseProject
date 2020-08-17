package com.app.kujacustomerapp.ui.account.changepassword

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.entity.request.account.ChangePasswordRequest
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(application: Application,
                                                  private val accountRepository: AccountRepository
) : ChangePasswordVariableViewModel(application){

    val successLiveData = MutableLiveData<Event<Boolean?>>()

    val errorLiveData = MutableLiveData<Event<String?>>()

    fun onSubmitClicked(){
        var isValid=true
        oldPasswordError = if (oldPassword.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isPasswordValid(oldPassword.toString())) {
                isValid = false
                R.string.enter_valid_password
            } else {
                null
            }
        }
        newPasswordError = if (newPassword.isNullOrEmpty()) {
            isValid = false
            R.string.enter_password
        } else {
            if (!isPasswordValid(newPassword.toString())) {
                isValid = false
                R.string.enter_valid_password
            } else {
                null
            }
        }
        confirmPasswordError = if (confirmPassword.isNullOrEmpty()) {
            isValid = false
            R.string.enter_password
        } else {
            if (!isPasswordValid(confirmPassword.toString())) {
                isValid = false
                R.string.enter_valid_password
            }
            else if(!newPassword.equals(confirmPassword)){
                isValid = false
                R.string.password_matches
            }
            else {
                null
            }
        }

        if (isValid) {
            changePassword()
        }

    }

    private fun isPasswordValid(toString: String): Boolean {
        return toString.length>4
    }

    private fun changePassword(){
        accountRepository.callChangePassword(ChangePasswordRequest(1,1,oldPassword,newPassword),object:Enqueue<Boolean?>{
            override fun onSuccess(call: Call<*>, response: Boolean?) {
                successLiveData.postValue(Event(response))
            }

            override fun onError(call: Call<*>, t: Throwable) {
            }

        })
    }

}