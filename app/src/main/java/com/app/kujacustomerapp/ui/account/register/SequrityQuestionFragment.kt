package com.app.kujacustomerapp.ui.account.register

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentSequrityQuestionBinding
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.utility.FragmentTagUtils
import kotlinx.android.synthetic.main.fragment_sequrity_question.*
import javax.inject.Inject


class SequrityQuestionFragment : BaseBindingFragment<FragmentSequrityQuestionBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var registerViewModel: RegisterViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    var itemSelectedPos:Int =-1

    override val contentView: Int
        get() = R.layout.fragment_sequrity_question

    override fun viewModelSetup() {
        registerViewModel =
            ViewModelProviders.of(this@SequrityQuestionFragment, factory).get(RegisterViewModel::class.java)
    }

    override fun initViews() {
        binding?.viewModel = registerViewModel
        registerViewModel?.callGetQuestion()
        setSecurityQuestionSelection()
        setOnSubmitClick()
        getDataFromRegisterFragment()
    }

    private fun getDataFromRegisterFragment() {
        val bundle: Bundle? = arguments
        if (bundle != null) {
            val name = bundle.getString(FragmentTagUtils.NAME)
            val email = bundle.getString(FragmentTagUtils.EMAIL)
            val phone = bundle.getString(FragmentTagUtils.PHONE)
            val dateOfBirth = bundle.getString(FragmentTagUtils.DATE_OF_BIRTH)
            registerViewModel?.firstName=name
            registerViewModel?.emailAddress=email
            registerViewModel?.phoneNo=phone
            registerViewModel?.date_Of_Birth=dateOfBirth
        }
    }

    private fun setSecurityQuestionSelection() {
        spinnerSecurityQuestion.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                itemSelectedPos=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    private fun setOnSubmitClick() {
        btnSubmit.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if(isValidate()){
                    registerViewModel?.callRegister()
                }
            }
        })
    }

    private fun isValidate(): Boolean {
        if(itemSelectedPos==-1){
            Toast.makeText(requireContext(),"select security question",Toast.LENGTH_SHORT).show()
            return false
        }
       else if(edtAnswer.text.isNullOrEmpty()){
            Toast.makeText(requireContext(),"Answer field is require",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            return true
        }
    }

    override fun initObservers() {
        registerViewModel?.successQuestionLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                var securityQuestionResponse:SecurityQuestionResponse=`object` as SecurityQuestionResponse
                registerViewModel?.securityQuestionResponse=securityQuestionResponse
                setSpinner(securityQuestionResponse.securityQuestion)
            }

        }))

        registerViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                if(`object` as Boolean){
                    Toast.makeText(requireContext(),"Sign Up Successfully",Toast.LENGTH_SHORT).show()
                }
            }

        }))
    }

    private fun setSpinner(securityQuestion: String?) {
        val ITEMS =
            arrayOf<String?>(securityQuestion)
        adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ITEMS)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSecurityQuestion.adapter=adapter
    }

}