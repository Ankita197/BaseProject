package com.app.kujacustomerapp.ui.account.forgotpassword

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentPasswordSendSuccessBinding
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import kotlinx.android.synthetic.main.fragment_password_send_success.*


class PasswordSendSuccessFragment : BaseBindingFragment<FragmentPasswordSendSuccessBinding>() {




    override val contentView: Int
        get() =R.layout.fragment_password_send_success

    override fun viewModelSetup() {

    }

    override fun initViews() {
       btnLogin.setOnClickListener(View.OnClickListener {
           startActivity(Intent(requireContext(),AccountActivity::class.java))
       })
    }

    override fun initObservers() {

    }



}