package com.app.kujacustomerapp.ui.account.register

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentRegisterBinding
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*
import javax.inject.Inject


open class RegisterFragment : BaseBindingFragment<FragmentRegisterBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var datePickerDialog:DatePickerDialog?=null
    var registerViewModel: RegisterViewModel? = null

    override val contentView: Int
        get() = R.layout.fragment_register

    override fun viewModelSetup() {
        registerViewModel =
            ViewModelProviders.of(this@RegisterFragment, factory).get(RegisterViewModel::class.java)
    }

    override fun initViews() {
        binding?.viewModel = registerViewModel
        setOnDateOfBirhSelect()
    }

    private fun setOnDateOfBirhSelect() {
        edtDateOfBirth.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            // date picker dialog
            datePickerDialog = activity?.let {
                DatePickerDialog(
                    it,
                    OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        edtDateOfBirth.setText(
                            dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                        )
                    }, year, month, day
                )
            }
            datePickerDialog?.show()
        }
    }

    override fun initObservers() {
        registerViewModel!!.successLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    navigateToDashBoard()
                }
            })
        )

        registerViewModel!!.errorLiveData.observe(
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

}