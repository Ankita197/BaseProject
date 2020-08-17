package com.app.kujacustomerapp.ui.dashboard

import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.utility.FragmentTagUtils

class DashboardActivity : BaseActivity() {
    override val contentView: Int
        protected get() = R.layout.activity_dashboard

    override fun viewModelSetup() {
        // no-op
    }

    override fun initView(): kotlin.Unit {
        switchFragment(DashboardFragment(), false, FragmentTagUtils.DASHBOARD_FRAGMENT)
    }
}