package com.app.kujacustomerapp.ui.account.changepassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentChangePasswordBinding
import com.app.kujacustomerapp.ui.base.BaseBindingFragment


open class ChangePasswordFragment : BaseBindingFragment<FragmentChangePasswordBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override val contentView: Int
        get() = R.layout.fragment_change_password

    override fun viewModelSetup() {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

}