package com.app.kujacustomerapp.ui.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.rfid.RfidFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionHistoryFragment
import com.app.kujacustomerapp.ui.dashboard.transfer_money.TransferMoneyFragment
import com.app.kujacustomerapp.utility.FragmentTagUtils
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.dialog_check_balance.view.*
import kotlinx.android.synthetic.main.dialog_success.view.*
import kotlinx.android.synthetic.main.dialog_success.view.btnOk
import javax.inject.Inject


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawerToggle:ActionBarDrawerToggle?=null

    companion object{
        var  toolbar: Toolbar?=null

    }


    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var dashboardViewModel: DashboardViewModel? = null
    var   customLayout:View?=null

    @Inject
    lateinit var accountSharedPrefs: AccountSharedPrefs
    override val contentView: Int
        protected get() = R.layout.activity_dashboard

    var alertDialog: AlertDialog.Builder?=null

    var dialog: AlertDialog?=null
    var dialogCheckBalance: AlertDialog?=null

    override fun viewModelSetup() {
        dashboardViewModel =
            ViewModelProviders.of(this, factory).get(DashboardViewModel::class.java)
    }



    override fun initView(): kotlin.Unit {
        DashboardActivity.toolbar=toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title="Transaction History"
        initLogoutDialog()
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setNavigationIcon(R.drawable.menu)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle?.isDrawerIndicatorEnabled=false
        setIconClickListner()
        switchFragment(TransactionHistoryFragment(), false, FragmentTagUtils.TRANSACTION_HISTORY_FRAGMENT)
        navView.setNavigationItemSelectedListener(this)
        initObservals()
        setDialogBalance()
    }

    private fun setIconClickListner() {
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.transactionHistory -> {
                supportActionBar?.title="Transaction History"
                switchFragment(TransactionHistoryFragment(), false, FragmentTagUtils.TRANSACTION_HISTORY_FRAGMENT)

            }
            R.id.logout->{
                showAlertDialog()
            }
            R.id.recorderRfid->{
                supportActionBar?.title="Recorder RFID"
                switchFragment(RfidFragment(), false, FragmentTagUtils.RFID_FRAGMENT)
            }
            R.id.transferMoney->{
                supportActionBar?.title="Transfer Money"
                switchFragment(TransferMoneyFragment(), false, FragmentTagUtils.TRANSFER_MONEY_FRAGMENT)
            }
            R.id.checkBalance->{
                dashboardViewModel?.checkMyBalance()
            }
            R.id.profile->{
                supportActionBar?.title="Profile"
                switchFragment(DashboardFragment(), false, FragmentTagUtils.DASHBOARD_FRAGMENT)
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
    private fun setDialogBalance(){

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
      customLayout= layoutInflater.inflate(R.layout.dialog_check_balance, null)
        alertDialog.setView(customLayout)
        customLayout?.rootView?.btnOk?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                dialogCheckBalance?.dismiss()
            }
        })

        dialogCheckBalance= alertDialog.create()
        dialogCheckBalance?.setCanceledOnTouchOutside(false)
    }
    private fun initObservals(){
        dashboardViewModel?.successLiveData?.observe(this,EventObserver<Any>(object :OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                var balance=`object` as Float
                    customLayout?.rootView?.tvWalletBalanceValue?.text=balance.toInt().toString()
                customLayout?.rootView?.tvKujaBalanceValue?.text=balance.toInt().toString()
                dialogCheckBalance?.show()

            }
        }))
    }
}