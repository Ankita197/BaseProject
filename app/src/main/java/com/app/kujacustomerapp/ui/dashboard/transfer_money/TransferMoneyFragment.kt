package com.app.kujacustomerapp.ui.dashboard.transfer_money

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentTransferMoneyBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.MakePaymentResponse
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import kotlinx.android.synthetic.main.dialog_success.view.*
import kotlinx.android.synthetic.main.dialog_success.view.btnOk
import kotlinx.android.synthetic.main.dialog_transfer_money.view.*
import kotlinx.android.synthetic.main.fragment_transfer_money.*
import javax.inject.Inject


class TransferMoneyFragment : BaseBindingFragment<FragmentTransferMoneyBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var alert: AlertDialog?=null
    var customLayout: View?=null


    var transferMoneyViewModel:TransferMoneyViewModel?=null


    override val contentView: Int
        get() = R.layout.fragment_transfer_money

    override fun viewModelSetup() {
        transferMoneyViewModel =
            ViewModelProviders.of(this@TransferMoneyFragment, factory).get(TransferMoneyViewModel::class.java)
    }

    override fun initViews() {
        binding?.transferMoneyViewModel=transferMoneyViewModel
        binding?.let { transferMoneyViewModel?.setBinding(it) }
        setDialogShowPayment()
       btnSend.setOnClickListener(object :View.OnClickListener{
           override fun onClick(v: View?) {
               if(transferMoneyViewModel?.onValidate()!!) {
                   transferMoneyViewModel?.makePayment()
               }
           }
       })
    }

    private fun setDialogShowPayment() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        customLayout = layoutInflater.inflate(R.layout.dialog_transfer_money, null)
        alertDialog.setView(customLayout)
        customLayout?.rootView?.btnOk?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                alert?.dismiss()
            }
        })

        alert= alertDialog.create()
        alert?.setCanceledOnTouchOutside(false)
    }

    override fun initObservers() {
        transferMoneyViewModel?.successLiveData?.observe(this,EventObserver<Any>(object :
            OnEventUnhandledContent{
            override fun onChanged(`object`: Any?) {
                var makePaymentResponse=`object` as MakePaymentResponse
                customLayout?.rootView?.tvTransactionIdValue?.text=makePaymentResponse.customerTrxID.toString()
                customLayout?.rootView?.tvAmountValue?.text=makePaymentResponse.amount?.toInt().toString()
                customLayout?.rootView?.tvReceiverValue?.text=makePaymentResponse.receiverPhoneNumber
                customLayout?.rootView?.btnState?.text=getStatus(makePaymentResponse.status)
                alert?.show()
            }

        }))
    }

    private fun getStatus(status: Int?): CharSequence? {
        if(status==1)
            return "Active"
        else
            return "InActive"
    }

}