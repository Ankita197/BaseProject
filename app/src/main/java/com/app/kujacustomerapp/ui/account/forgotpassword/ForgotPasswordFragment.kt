package com.app.kujacustomerapp.ui.account.forgotpassword

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentForgotPasswordBinding
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.utility.FragmentTagUtils
import javax.inject.Inject

class ForgotPasswordFragment : BaseBindingFragment<FragmentForgotPasswordBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var forgotPasswordViewModel: ForgotPasswordViewModel? = null

    override val contentView: Int
        get() = R.layout.fragment_forgot_password

    override fun viewModelSetup() {
        forgotPasswordViewModel =
            ViewModelProviders.of(this@ForgotPasswordFragment, factory)
                .get(ForgotPasswordViewModel::class.java)
    }

    override fun initViews() {
        binding?.viewModel = forgotPasswordViewModel
    }

    override fun initObservers() {
        forgotPasswordViewModel!!.successLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    if(status as Boolean){
                        (activity as AccountActivity).switchFragment(
                            PasswordSendSuccessFragment(),
                            true,
                            FragmentTagUtils.PASSWORD_SEND_SUCCESS_FRAGMENT
                        )
                    }
                }
            })
        )

        forgotPasswordViewModel!!.errorLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    //handle Error
                }
            })
        )
    }

}