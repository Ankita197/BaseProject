package com.app.kujacustomerapp.ui.dashboard.rfid

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_rfid.*
import javax.inject.Inject


open class RfidFragment : BaseBindingFragment<FragmentRfidBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var rfidViewModel: RfidViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    val itemRfidAdapter=ItemRfidAdapter()
    var alert: AlertDialog?=null

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
        rfidViewModel?.getDeviceData()
        setDialodView()
        rvItemDevices.adapter=itemRfidAdapter
        btnSubmit.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                alert?.show()
            }
        })
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
                    Toast.makeText(requireContext(),"reodering request is submitted",Toast.LENGTH_SHORT).show()
                }

            }

        }))
    }
    }


