package com.app.kujacustomerapp.ui.dashboard.rfid

import android.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentRfidBinding
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemRfidAdapter
import kotlinx.android.synthetic.main.dialog_submission.view.*
import kotlinx.android.synthetic.main.dialog_success.view.*
import kotlinx.android.synthetic.main.fragment_rfid.*
import javax.inject.Inject


open class RfidFragment : BaseBindingFragment<FragmentRfidBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var rfidViewModel: RfidViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    val itemRfidAdapter=ItemRfidAdapter()
    var alert: AlertDialog?=null
    var alertSuccess: AlertDialog?=null

    private fun setSpinner() {
      val item=ArrayList<String>()
        item.add("Card")
        item.add("bend")
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, item)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDeviceType.adapter=adapter
    }

    override val contentView: Int
        get() = R.layout.fragment_rfid

    override fun viewModelSetup() {
        rfidViewModel= ViewModelProviders.of(requireActivity(), factory).get(RfidViewModel::class.java)
    }

    override fun initViews() {
        setSpinner()
        setItemSelectedListner()
        rfidViewModel?.getDeviceData()
        setDialodView()
        setSuccessDialog()
        rvItemDevices.adapter=itemRfidAdapter
        btnSubmit.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                alert?.show()
            }
        })
    }

    private fun setItemSelectedListner() {
       spinnerDeviceType.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
               rfidViewModel?.position=(position+1)
                when (position) {
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    private fun setSuccessDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater.inflate(R.layout.dialog_success, null)
        alertDialog.setView(customLayout)
        customLayout.btnOk.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
               alertSuccess?.dismiss()
            }
        })

        alertSuccess= alertDialog.create()
        alertSuccess?.setCanceledOnTouchOutside(false)

    }


    private fun setDialodView() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater.inflate(R.layout.dialog_submission, null)
        alertDialog.setView(customLayout)
       customLayout.btnYes.setOnClickListener(object :View.OnClickListener{
           override fun onClick(v: View?) {
               rfidViewModel?.setReOderingDevice()
           }
       })
        customLayout.btnNo.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                alert?.dismiss()
            }
        })
        alert= alertDialog.create()
        alert?.setCanceledOnTouchOutside(false)

    }

    override fun initObservers() {
        rfidViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(`object`: Any?) {
              var listOfDeviceData= (`object` as ArrayList<DeviceData>)
                itemRfidAdapter.setList(listOfDeviceData)
            }

        }))

        rfidViewModel?.successReOrderingRequestLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(`object`: Any?) {
                if(`object` as Boolean){
                    alert?.dismiss()
                    alertSuccess?.show()
                }

            }

        }))
    }
    }


