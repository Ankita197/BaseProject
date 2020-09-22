package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.annotation.SuppressLint
import android.view.View
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentDashboardBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import kotlinx.android.synthetic.main.fragment_daily.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DailyFragment(var newlist: ArrayList<TransactionData>) : BaseBindingFragment<FragmentDashboardBinding>() {

    var year:Int=0
    var month:Int=0
    var dayOfWeek:Int=0
    var listOfTransactionData=ArrayList<TransactionData>()
    var itemTransactionAdapter:ItemTransactionAdapter?=null
    var filteredList: List<TransactionData>?=null

    override val contentView: Int
        get() = R.layout.fragment_daily

    override fun viewModelSetup() {
    }

    override fun initViews() {
        itemTransactionAdapter= ItemTransactionAdapter()
        listOfTransactionData=newlist
        rvItemTransaction.adapter=itemTransactionAdapter
        setFilteredList()
        btnViewTransaction.setOnClickListener(View.OnClickListener {
            setFilteredList()
        })
    }

    override fun initObservers() {
    }

    private fun setFilteredList(){
        filteredList=null
        filteredList=newlist.filter { getDate().equals(setDate(it.trxDate)) }
        itemTransactionAdapter?.setList(filteredList as ArrayList<TransactionData>)
    }
    private  fun setDate(date: String?): String?{
        var reformattedStr = ""
        @SuppressLint("SimpleDateFormat") val fromUser =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        @SuppressLint("SimpleDateFormat") val myFormat =
            SimpleDateFormat("yyyy-MM-dd")
        if(date!=null) {
            try {
                reformattedStr =
                    myFormat.format(Objects.requireNonNull(fromUser.parse(date)))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return reformattedStr
    }
    fun getDate():String{
        val builder = StringBuilder()
        year=datePicker.year
        month=datePicker.month+1
        dayOfWeek=datePicker.dayOfMonth
        builder.append((datePicker.year).toString() + "-") //month is 0 based
        if(month<=9){
            builder.append("0"+(month).toString() + "-")
        }
        else{
            builder.append((datePicker.month+1).toString() + "-")
        }
        if(dayOfWeek<=9){
            builder.append("0"+datePicker.dayOfMonth)
        }
        else{
            builder.append(datePicker.dayOfMonth)
        }
        return builder.toString()

    }


}