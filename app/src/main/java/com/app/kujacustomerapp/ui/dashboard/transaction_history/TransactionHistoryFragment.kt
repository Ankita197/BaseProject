package com.app.kujacustomerapp.ui.dashboard.transaction_history

import android.annotation.SuppressLint
import android.util.Log
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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.kal.rackmonthpicker.RackMonthPicker
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


open class TransactionHistoryFragment() : BaseBindingFragment<FragmentTransactionHistoryBinding>(){

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var itemTransactionAdapter:ItemTransactionAdapter?=null
    var list= mutableListOf<TransactionData>()
    var filteredList= listOf<String>()
    var currentDate:String=""
    var year:Int=0
    var month:Int=0
    var dayOfWeek:Int=0
    var listOfDate=mutableListOf<String>()
    var position:Int=0

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
        setTabSelectedListner()
        btnViewTransaction.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                getDate()
                if(position==1) {
                    filteredList= listOfDate.filter {  getMonth(it)==month}
                    for (data in filteredList){
                        Log.d("___@___",data+"date is")
                    }
                }
            }
        })
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

    private fun setTabSelectedListner() {
        tlItemTransaction.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                showMonthSelectDialog()
                position=tab.position
                setListAccordingSelectedPos(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun showMonthSelectDialog() {
        RackMonthPicker(activity)
            .setLocale(Locale.ENGLISH)
            .setPositiveButton { month, startDate, endDate, year, monthLabel -> }
            .setNegativeButton { }.show()
    }

    private fun setListAccordingSelectedPos(position: Int) {

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

    fun getDate():String{
        val builder = StringBuilder()
        year=datePicker.year
        month=datePicker.month+1
        dayOfWeek=datePicker.dayOfMonth
        builder.append((datePicker.year).toString() + "-") //month is 0 based
        builder.append((datePicker.month+1).toString() + "-")
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
                for(data in list) {
                    data.trxDate?.let { setDate(it)?.let { listOfDate.add(it) } }
                }
            }
        }))

    }


}