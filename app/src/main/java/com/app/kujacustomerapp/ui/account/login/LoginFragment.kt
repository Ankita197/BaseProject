package com.app.kujacustomerapp.ui.account.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentLoginBinding
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.account.forgotpassword.ForgotPasswordFragment
import com.app.kujacustomerapp.ui.account.register.RegisterFragment
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import com.app.kujacustomerapp.utility.Constants
import com.app.kujacustomerapp.utility.FragmentTagUtils.FORGOT_PASSWORD_FRAGMENT
import com.app.kujacustomerapp.utility.FragmentTagUtils.REGISTER_FRAGMENT
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

open class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var loginViewModel: LoginViewModel? = null

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callbackManager: CallbackManager? = null

    override val contentView: Int
        get() = R.layout.fragment_login

    override fun viewModelSetup() {
        loginViewModel =
            ViewModelProviders.of(this@LoginFragment, factory).get(LoginViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun initViews() {
        binding?.viewModel = loginViewModel
        binding?.let { loginViewModel?.setBinding(it) }
        setSpannableText()


// ForegroundColorSpan(resources.getColor(R.color.blue_3866ab))


        binding?.tvForgotPassword?.setOnClickListener {
            (activity as AccountActivity).switchFragment(
                ForgotPasswordFragment(),
                true,
                FORGOT_PASSWORD_FRAGMENT
            )

        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setSpannableText(){
        val spannable = SpannableStringBuilder("Don’t have an account? Sign Up")
        val typeface: Typeface? = activity?.let { ResourcesCompat.getFont(it, R.font.montserrat_semibold) }
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // We display a Toast. You could do anything you want here.
                (activity as AccountActivity).switchFragment(
                    RegisterFragment(),
                    true,
                    REGISTER_FRAGMENT
                )
                Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        spannable.setSpan(clickableSpan,23,30,0)
        spannable.setSpan(
            typeface?.let { TypefaceSpan(it) },
            23, 30,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.blue_3866ab)),
            23, 30,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        tvSignUp.movementMethod = LinkMovementMethod.getInstance()
        tvSignUp.text=spannable


    }

    override fun initObservers() {
        loginViewModel!!.successLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    if(status!! as Boolean) {
                        navigateToDashBoard()
                    }
                    else{
                        Toast.makeText(activity,"Invalid phone or password",Toast.LENGTH_SHORT).show()
                    }
                }
            })
        )

        loginViewModel!!.errorLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    //handle Error
                }
            })
        )

    }

    private fun navigateToDashBoard() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
    }

    private fun initLogin() {
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(resources.getString(R.string.google_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val accessToken: AccessToken = AccessToken.getCurrentAccessToken()
                    if (!accessToken.isExpired) {
                        //do things here
                    }
                }

                override fun onCancel() {
                }

                override fun onError(exception: FacebookException) {

                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RequestCodes.SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                //handle login here
            } catch (e: ApiException) {
            }
        }
    }


}