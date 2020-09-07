package com.app.kujacustomerapp.ui.dashboard

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionHistoryFragment
import com.app.kujacustomerapp.utility.FragmentTagUtils
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawerToggle:ActionBarDrawerToggle?=null
    override val contentView: Int
        protected get() = R.layout.activity_dashboard

    override fun viewModelSetup() {
        // no-op
    }

    override fun initView(): kotlin.Unit {
        setSupportActionBar(toolbar)
        supportActionBar?.title="Profile"
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle?.isDrawerIndicatorEnabled=false
        setIconClickListner()
        drawerToggle?.setHomeAsUpIndicator(R.drawable.menu);
        switchFragment(DashboardFragment(), false, FragmentTagUtils.DASHBOARD_FRAGMENT)
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setIconClickListner() {
        drawerToggle?.toolbarNavigationClickListener = object : View.OnClickListener {
            override  fun onClick(view: View?) {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.transactionHistory -> {
                switchFragment(TransactionHistoryFragment(), false, FragmentTagUtils.TRANSACTION_HISTORY_FRAGMENT)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}