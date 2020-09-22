package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentMonthlyBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import com.kal.rackmonthpicker.RackMonthPicker
import kotlinx.android.synthetic.main.fragment_monthly.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MonthlyFragment(var list: ArrayList<TransactionData>) : BaseBindingFragment<FragmentMonthlyBinding>() {

    var selectedMonth:Int=0
    var monthlyDataList= ArrayList<TransactionData>()
    var filteredList: List<TransactionData>?=null
    var itemTransactionAdapter:ItemTransactionAdapter?=null

    override val contentView: Int
        get() = R.layout.fragment_monthly


    override fun viewModelSetup() {
    }

    override fun initViews() {
        monthlyDataList=list
        itemTransactionAdapter= ItemTransactionAdapter()
        rvItemTransaction.adapter=itemTransactionAdapter
        setFilteredList(getCurrentMonth())
        tvSelectMonth.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                showMonthSelectDialog()
            }

        })
        btnViewTransaction.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                setFilteredList(selectedMonth)
            }
        })
    }

    private fun setFilteredList( month:Int) {
        filteredList=null
        filteredList= monthlyDataList.filter {  it.trxDate?.let { it1 -> setDate(it1)?.let { it2 ->
            getMonth(
                it2
            )
        } } ==month}
        if(filteredList!=null){
            itemTransactionAdapter?.setList(filteredList as ArrayList<TransactionData>)
        }

    }

    private fun showMonthSelectDialog() {
        RackMonthPicker(activity)
            .setLocale(Locale.ENGLISH)
            .setPositiveButton { month, startDate, endDate, year, monthLabel ->
                selectedMonth=month
            }
            .setNegativeButton {  dialog -> dialog.dismiss()  }.show()
    }

    override fun initObservers() {

    }
   private fun getCurrentMonth():Int{
        val now = Calendar.getInstance()
        return (now.get(Calendar.MONTH) + 1)

    }

    private  fun setDate(date: String): String? {
        var reformattedStr = ""
        @SuppressLint("SimpleDateFormat") val fromUser =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        @SuppressLint("SimpleDateFormat") val myFormat =
            SimpleDateFormat("yyyy-MM-dd")
        try {
            reformattedStr =
                myFormat.format(Objects.requireNonNull(fromUser.parse(date)))
        } catch (e: ParseException)
        {
            e.printStackTrace()
        }
        return reformattedStr
    }
    private fun getMonth(dot:String):Int?{
        var month: Int? = 0
        var dd: Int? = 0
        var yer: Int? = 0
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = sdf.parse(dot)
            val cal = Calendar.getInstance()
            cal.time = d
            month = (cal[Calendar.MONTH] + 1)
            dd = (cal[Calendar.DATE])
            yer = (cal[Calendar.YEAR])

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return month
    }

}