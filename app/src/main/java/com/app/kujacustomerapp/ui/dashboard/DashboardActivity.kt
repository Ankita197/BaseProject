package com.app.kujacustomerapp.ui.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.ui.dashboard.rfid.RfidFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionHistoryFragment
import com.app.kujacustomerapp.utility.FragmentTagUtils
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawerToggle:ActionBarDrawerToggle?=null

    @Inject
    lateinit var accountSharedPrefs: AccountSharedPrefs
    override val contentView: Int
        protected get() = R.layout.activity_dashboard

    var alertDialog: AlertDialog.Builder?=null

    var dialog: AlertDialog?=null

    override fun viewModelSetup() {
        // no-op
    }

    override fun initView(): kotlin.Unit {
        setSupportActionBar(toolbar)
        supportActionBar?.title="Profile"
        initLogoutDialog()
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
            R.id.logout->{
                showAlertDialog()
            }
            R.id.recorderRfid->{
                switchFragment(RfidFragment(), false, FragmentTagUtils.RFID_FRAGMENT)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun initLogoutDialog(){
        alertDialog = AlertDialog.Builder(this)
        alertDialog?.setTitle("Logout")
        alertDialog?.setMessage("Are you sure you want to logout")
        alertDialog?.setPositiveButton( "OK",
            DialogInterface.OnClickListener { dialog, which ->
                accountSharedPrefs.isUserLoggedIn=false
                startActivity(Intent(this,AccountActivity::class.java))
                finish()
            })
        alertDialog?.setNegativeButton( "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        dialog=alertDialog?.create()
    }

    private fun showAlertDialog() {

        dialog?.show()
    }
}