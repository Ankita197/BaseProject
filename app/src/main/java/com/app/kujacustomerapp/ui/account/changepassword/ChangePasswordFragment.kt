package com.app.kujacustomerapp.ui.account.changepassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentChangePasswordBinding
import com.app.kujacustomerapp.ui.account.forgotpassword.ForgotPasswordViewModel
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


open class ChangePasswordFragment : BaseBindingFragment<FragmentChangePasswordBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var changePasswordViewModel: ChangePasswordViewModel? = null

    override val contentView: Int
        get() = R.layout.fragment_change_password

    override fun viewModelSetup() {
        changePasswordViewModel =
            ViewModelProviders.of(this@ChangePasswordFragment, factory)
                .get(ChangePasswordViewModel::class.java)
    }

    override fun initViews() {
      binding?.viewModel=changePasswordViewModel
        DashboardActivity.toolbar?.title="Change Password "
        DashboardActivity.toolbar?.setNavigationIcon(R.drawable.ic_back)
        DashboardActivity.toolbar?.setNavigationOnClickListener(View.OnClickListener {

        })
    }

    override fun initObservers() {
        changePasswordViewModel?.successLiveData?.observe(this,EventObserver<Any>(object :OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                if(`object` as Boolean){
                    Toast.makeText(activity,"Password Change SuccessFully",Toast.LENGTH_SHORT).show()
                }
            }

        }))
    }

}