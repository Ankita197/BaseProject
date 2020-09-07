package com.app.kujacustomerapp.ui.dashboard.transaction_history

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentTransactionHistoryBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import javax.inject.Inject

open class TransactionHistoryFragment() : BaseBindingFragment<FragmentTransactionHistoryBinding>(){

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var itemTransactionAdapter:ItemTransactionAdapter?=null
    var list= mutableListOf<TransactionData>()
    var currentDate:String=""

    var transactionViewModel: TransactionViewModel? = null
    override val contentView: Int
        get() = R.layout.fragment_transaction_history

    override fun viewModelSetup() {
        transactionViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(TransactionViewModel::class.java)
    }

    override fun initViews() {
        transactionViewModel?.getTransactionData()
        itemTransactionAdapter= ItemTransactionAdapter()
        btnViewTransaction.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

            }

        })

    }

    fun getDate():String{
        val builder = StringBuilder()
        builder.append((datePicker.year + 1).toString() + "-") //month is 0 based

        builder.append(datePicker.month.toString() + "-")
        builder.append(datePicker.dayOfMonth)
        return builder.toString()
    }

    override fun initObservers() {
        transactionViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(status: Any?) {

                list=status as ArrayList<TransactionData>
                itemTransactionAdapter?.list=list
                rvItemTransaction.adapter=itemTransactionAdapter
            }
        }))

    }


}