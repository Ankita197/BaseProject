package com.app.kujacustomerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var accountSharedPrefs: AccountSharedPrefs
    companion object {
        const val DELAY: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val contentView: Int
        get() = R.layout.activity_splash

    override fun viewModelSetup() {

    }

    override fun initView() {
        Handler().postDelayed(
            {
                if(accountSharedPrefs.isUserLoggedIn) {
                    startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                    finish()
                }
                else{
                    startActivity(Intent(this@SplashActivity, AccountActivity::class.java))
                    finish()
                }

            }, DELAY
        )

    }
}
