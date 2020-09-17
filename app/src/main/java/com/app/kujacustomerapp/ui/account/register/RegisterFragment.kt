package com.app.kujacustomerapp.ui.account.register

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentRegisterBinding
import com.app.kujacustomerapp.persistance.AccountSharedPrefs
import com.app.kujacustomerapp.ui.account.AccountActivity
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.DashboardActivity
import com.app.kujacustomerapp.utility.FragmentTagUtils
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*
import javax.inject.Inject


open class RegisterFragment : BaseBindingFragment<FragmentRegisterBinding>() {

    private val REQUEST_CODE: Int=200

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val sequrityQuestionFragment=SequrityQuestionFragment()
    val args = Bundle()

    @Inject
    lateinit var accountSharedPrefs:AccountSharedPrefs

    var builder:AlertDialog.Builder?=null
    var datePickerDialog:DatePickerDialog?=null
    var registerViewModel: RegisterViewModel? = null
    private var adapter: ArrayAdapter<String>? = null

    override val contentView: Int
        get() = R.layout.fragment_register

    override fun viewModelSetup() {
        registerViewModel =
            ViewModelProviders.of(this@RegisterFragment, factory).get(RegisterViewModel::class.java)
    }

    override fun initViews() {
        binding?.viewModel = registerViewModel
        setOnDateOfBirhSelect()
        setDocumentUploadListner()
        setSpinner()
        setSelectedItem()
        binding?.btnUploadsDocuments?.setOnClickListener { builder?.show() }

        binding?.btnSignUP?.setOnClickListener {
            if(isValidate()) {
                setArgs()
                navigateToSecurityQuestionFragment()

            }
        }
    }

    private fun navigateToSecurityQuestionFragment() {
        (activity as AccountActivity).switchFragment(
            sequrityQuestionFragment,
            true,
            FragmentTagUtils.SECURITY_QUESTION_FRAGMENT
        )
    }

    private fun setArgs() {
        args.putString(FragmentTagUtils.NAME, edtName.text.toString())
        args.putString(FragmentTagUtils.EMAIL, edtEmail.text.toString())
        args.putString(FragmentTagUtils.DATE_OF_BIRTH, edtDateOfBirth.text.toString())
        args.putString(FragmentTagUtils.PHONE, edtPhoneNo.text.toString())
        sequrityQuestionFragment.arguments=(args)
    }

    private fun printToast(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}
    private fun isValidate(): Boolean {
       if( edtName.text.isNullOrEmpty()){
           printToast("please enter name")
           return false
       }
       else if( edtDateOfBirth.text.isNullOrEmpty()){
            printToast("please enter Date Of Birth")
            return false
        }
       else if( edtEmail.text.isNullOrEmpty()){
           printToast("please enter email")
           return false
       }
       else if( !Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()){
           printToast("please enter valid email")
           return false
       }
       else if( edtNationalId.text.isNullOrEmpty()){
           printToast("please enter national id")
           return false
       }
       else if( edtPhoneNo.text.isNullOrEmpty()){
           printToast("please enter phone number")
           return false
       }
        else{
           return true
       }

    }

    private fun setSpinner() {
        val ITEMS =
            arrayOf<String?>("Uganda")
        adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ITEMS)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter=adapter

    }

    private fun setSelectedItem() {
       spinnerCountry.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
//                registerViewModel.setProductType(position.toString())
//                when (position) {
//                    0, 1 -> binding.spinnerProductType.setError(null)
//                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    private fun setDocumentUploadListner() {
        builder = AlertDialog.Builder(requireContext())
        builder?.setTitle("Upload Document")
        builder?.setMessage("Select File or Capture Photo to upload id")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder?.setPositiveButton("Select") { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.yes, Toast.LENGTH_SHORT).show()
            if (askForPermissions()) {
                openGalleryForImage()
            }
        }
        builder?.setNegativeButton("Capture") { dialog, which ->
            Toast.makeText(requireContext(),
                "Maybe", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
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
                           year.toString() + "-" + (monthOfYear + 1) + "-" +  dayOfMonth.toString()
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
    fun isPermissionsAllowed(): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    fun askForPermissions(): Boolean {
        if (!isPermissionsAllowed()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity as Activity,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity as Activity,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_CODE)
            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>,grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission is granted, you can perform your operation here
                } else {
                    // permission is denied, you can ask for permission again, if you want
                    //  askForPermissions()
                }

                return
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
           // imageView.setImageURI(data?.data) // handle chosen image
            Log.d("___@___",data?.data.toString())
        }
    }
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton("App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    // send to app settings if permission is denied permanently
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", requireContext().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                })
            .setNegativeButton("Cancel",null)
            .show()
    }

    private fun navigateToDashBoard() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
    }

}