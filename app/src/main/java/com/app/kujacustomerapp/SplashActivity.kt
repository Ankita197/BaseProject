package com.app.kujacustomerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.kujacustomerapp.ui.base.BaseActivity
import com.app.kujacustomerapp.ui.account.AccountActivity

class SplashActivity : BaseActivity() {

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
                startActivity(Intent(this@SplashActivity, AccountActivity::class.java))
                finish()
            }, DELAY
        )

    }
}
