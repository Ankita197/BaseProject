package com.app.kujacustomerapp.ui.account.register

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentSequrityQuestionBinding
import com.app.kujacustomerapp.remote.entity.request.account.SecurityQuestionRequestUpdate
import com.app.kujacustomerapp.remote.entity.response.account.SecurityQuestionResponse
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionHistoryFragment
import com.app.kujacustomerapp.utility.FragmentTagUtils
import com.facebook.login.LoginFragment
import kotlinx.android.synthetic.main.dialog_data_submmited.view.*
import kotlinx.android.synthetic.main.dialog_success.view.*
import kotlinx.android.synthetic.main.fragment_sequrity_question.*
import okhttp3.MultipartBody
import javax.inject.Inject


class SequrityQuestionFragment( var photos: ArrayList<MultipartBody.Part>) : BaseBindingFragment<FragmentSequrityQuestionBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var registerViewModel: RegisterViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    var itemSelectedPos:Int =-1
    var alert: AlertDialog?=null
    var customLayout: View?=null
    var securityQuestionResponse:SecurityQuestionResponse?=null


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
        if(photos.size!=0) {

            registerViewModel?.setListPhotos(photos)
        }
        setDialogDataSubmitted()
    }
    private fun setDialogDataSubmitted() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        customLayout = layoutInflater.inflate(R.layout.dialog_data_submmited, null)
        alertDialog.setView(customLayout)
        customLayout?.rootView?.btnSuccess?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                (activity as AccountActivity).finish()
                startActivity(Intent(activity,AccountActivity::class.java))
            }
        })

        alert= alertDialog.create()
        alert?.setCanceledOnTouchOutside(false)
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

                    if (isValidate()) {
                        if(photos.size!=0) {
                            registerViewModel?.callRegister()
                        }
                        else{
                            registerViewModel?.securityQuestionRequestUpdate= SecurityQuestionRequestUpdate("1","1",
                               securityQuestionResponse?.securityQuestionID.toString(), edtAnswer.text.toString())
                            registerViewModel?.callUpdateSecurityQuestion()
                        }
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
                securityQuestionResponse=`object` as SecurityQuestionResponse
                registerViewModel?.securityQuestionResponse=securityQuestionResponse
                setSpinner(securityQuestionResponse?.securityQuestion)
            }

        }))

        registerViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                if(`object` as Boolean){
                  alert?.show()

                }
            }

        }))

        registerViewModel?.successSecurityQuestionUpdateLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                if(`object` as Int==1){
                    Log.d("___@___","data updated Successfully")

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