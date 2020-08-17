package com.app.kujacustomerapp.ui.account

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.ui.account.login.LoginFragment
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.utility.FragmentTagUtils.LOGIN_FRAGMENT
import javax.inject.Inject

class AccountActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var signUpViewModel: SignUpViewModel? = null

    override val contentView: Int
        get() = R.layout.activity_account

    override fun viewModelSetup() {
        signUpViewModel =
            ViewModelProviders.of(this@AccountActivity, factory).get(SignUpViewModel::class.java)
    }

    override fun initView() {
        switchFragment(LoginFragment(), false, LOGIN_FRAGMENT)
    }

}