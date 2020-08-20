package com.app.kujacustomerapp.ui.account.register

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.request.account.SecurityQuestionRequest
import com.app.kujacustomerapp.remote.entity.request.account.SignUpRequest
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.repository.account.AccountRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

open class RegisterViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : RegisterVariableViewModel(application) {

    val successLiveData = MutableLiveData<Event<Boolean?>>()

    val successQuestionLiveData = MutableLiveData<Event<SecurityQuestionResponse?>>()

    val errorLiveData = MutableLiveData<Event<String?>>()

    var firstName:String?=null
    var emailAddress:String?=null
    var phoneNo:String?=null
    var date_Of_Birth:String?=null
    var securityQuestionResponse:SecurityQuestionResponse?=null
    fun onSignUpClicked() {
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
        phoneError = if (phone.isNullOrEmpty()) {
            isValid = false
            R.string.enter_mobile
        } else {
            if (!isPhoneValid(password!!)) {
                isValid = false
                R.string.enter_valid_mobile
            } else {
                null
            }
        }
        dateOfBirthError = if (dateOfBirth.isNullOrEmpty()) {
            isValid = false
            R.string.please_enter_date_of_birth
        } else {
           null
        }

        postalCodeError = if (postalCode.isNullOrEmpty()) {
            isValid = false
            (R.string.enter_postal_code)
        } else {
            null
        }

        nationalIdError = if (nationalId.isNullOrEmpty()) {
            isValid = false
            (R.string.enter_natonal_id)
        } else {
            null
        }
        if (isValid) {
            callRegister()
        }
    }

     fun callRegister() {
        showProgress = true
        accountRepository.callRegister(
            SignUpRequest(emailAddress, firstName, date_Of_Birth,"1","121,qwerty","Ahmedabad","Gujarat",
                "229","123456",phoneNo,securityQuestionResponse?.securityQuestionID.toString(),
                    securityQuestionResponse?.securityAnswer,3,"12334574"),
            object :
                Enqueue<Boolean?> {
                override fun onSuccess(
                    call: Call<*>,
                    response: Boolean?
                ) {
                    showProgress = false
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

    private fun isNameValid(name: String): Boolean {
        return name.length > 5
    }

    private fun isPhoneValid(email: String): Boolean {
        return email.length==10
    }

    private fun isEmailValid(email: String): Boolean {
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun callGetQuestion(){
        accountRepository.callGetSecurityQuestion(SecurityQuestionRequest(1,1),object :Enqueue<SecurityQuestionResponse?>{
            override fun onSuccess(call: Call<*>, response: SecurityQuestionResponse?) {
                successQuestionLiveData.postValue(Event(response))

            }

            override fun onError(call: Call<*>, t: Throwable) {

            }
        })
    }


}