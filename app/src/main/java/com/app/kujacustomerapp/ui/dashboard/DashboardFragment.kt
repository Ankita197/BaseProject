package com.app.kujacustomerapp.ui.dashboard

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentDashboardBinding
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.remote.entity.request.account.SignUpRequest
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.account.changepassword.ChangePasswordFragment
import com.app.kujacustomerapp.ui.account.register.RegisterFragment
import com.app.kujacustomerapp.ui.account.register.SequrityQuestionFragment
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.utility.FragmentTagUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.spinnerCountry
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.nav_header.view.*
import okhttp3.MultipartBody
import javax.inject.Inject

open class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var dashboardViewModel: DashboardViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    private var arrayList=ArrayList<MultipartBody.Part>()

    @Inject
    lateinit var accountSharedPrefs: AccountSharedPrefs
    private var usersList = ArrayList<DashboardData.User>()

    override val contentView: Int
        get() = R.layout.fragment_dashboard

    override fun initViews() {
    initHeaders()
        setSpinner()
        setSelectedItem()
        btnChangePassword.setOnClickListener(View.OnClickListener {
            (activity as DashboardActivity).switchFragment(
               ChangePasswordFragment(),
                true,
                FragmentTagUtils.CHANGE_PASSWORD_FRAGMENT
            )
        })
        btnChangeSecurityQuestion.setOnClickListener(View.OnClickListener {
            (activity as DashboardActivity).switchFragment(
                SequrityQuestionFragment(arrayList),
                true,
                FragmentTagUtils.SECURITY_QUESTION_FRAGMENT
            )
        })
        setUserDate()


    }

    private fun setUserDate() {
        val gson= Gson()
        var  userData: SignUpRequest =gson.fromJson(accountSharedPrefs.profileData,SignUpRequest::class.java)

    }

    private fun setSelectedItem() {
        spinnerCountry.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
//                registerViewModel.setProductType(position.toString())
//                when (position) {
//                    0, 1 -> binding.spinnerProductType.setError(null)
//                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    private fun setSpinner() {
        val ITEMS =
            arrayOf<String?>("Uganda")
        adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ITEMS)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter=adapter

    }
    private fun initHeaders() {
        header.rootView.nav_view.visibility=View.GONE
    }

    override fun initObservers() {

    }

    override fun viewModelSetup() {
        dashboardViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(DashboardViewModel::class.java)
    }
}