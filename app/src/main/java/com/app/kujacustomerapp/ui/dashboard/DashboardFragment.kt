package com.app.kujacustomerapp.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentDashboardBinding
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.remote.entity.response.account.UserData
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.google.gson.Gson
import javax.inject.Inject

open class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var dashboardViewModel: DashboardViewModel? = null

    @Inject
    lateinit var accountSharedPrefs: AccountSharedPrefs
    private var usersList = ArrayList<DashboardData.User>()

    override val contentView: Int
        get() = R.layout.fragment_dashboard

    override fun initViews() {
    val gson= Gson()
      var  userData: UserData =gson.fromJson(accountSharedPrefs.userData,UserData::class.java)
        binding?.user=userData.user

    }

    override fun initObservers() {

    }

    override fun viewModelSetup() {
        dashboardViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(DashboardViewModel::class.java)
    }
}