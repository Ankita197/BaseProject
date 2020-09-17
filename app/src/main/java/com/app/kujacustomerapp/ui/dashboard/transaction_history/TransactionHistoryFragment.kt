package com.app.kujacustomerapp.ui.dashboard.transaction_history

import android.util.Log
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentTransactionHistoryBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionFragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


open class TransactionHistoryFragment() : BaseBindingFragment<FragmentTransactionHistoryBinding>(){



    var itemTransactionAdapter:ItemTransactionAdapter?=null
    var list= ArrayList<TransactionData>()
    var adapter:ItemTransactionFragmentAdapter?=null
    var newTransactionDataList= ArrayList<TransactionData>()
    var filteredList= listOf<String>()
    var selectedMonth:Int=0;
    var year:Int=0
    var month:Int=0
    var dayOfWeek:Int=0
    var listOfDate=mutableListOf<String>()
    var position:Int=0

    var transactionViewModel: TransactionViewModel? = null
    override val contentView: Int
        get() = R.layout.fragment_transaction_history

    override fun viewModelSetup() {
    }

    override fun initViews() {
        setTabsItem()
        setViewPager()
        transactionViewModel?.getTransactionData()
        itemTransactionAdapter= ItemTransactionAdapter()
        setTabSelectedListner()
//        btnViewTransaction.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                if(position==1) {
//                    filteredList= listOfDate.filter {  getMonth(it)==selectedMonth}
//                    setNewList(filteredList)
//                }
//                if(position==3){
//                    Log.d("___@___","date is"+getDate())
//                    filteredList= listOfDate.filter { it == getDate() }
//                    for(data in filteredList){
//                        Log.d("___@___","date is"+getDate())
//                    }
//                    setNewList(filteredList)
//                }
//            }
//        })
    }

    private fun setViewPager() {
        adapter = ItemTransactionFragmentAdapter(activity!!,childFragmentManager, tlItemTransaction!!.tabCount)
        container!!.adapter = adapter
    }

    //set tab item in tab layout
    private fun setTabsItem() {
        tlItemTransaction!!.addTab(tlItemTransaction!!.newTab().setText("Yearly"))
        tlItemTransaction!!.addTab(tlItemTransaction!!.newTab().setText("Monthly"))
        tlItemTransaction!!.addTab(tlItemTransaction!!.newTab().setText("Weekly"))
        tlItemTransaction!!.addTab(tlItemTransaction!!.newTab().setText("Daily"))
        tlItemTransaction!!.tabGravity = TabLayout.GRAVITY_FILL
    }

//    private fun setNewList(filteredList: List<String>) {
//        for(data in list){
//            for (trxDate in filteredList){
//                if(trxDate.equals(data.trxDate?.let { setDate(it) })){
//                    if(!newTransactionDataList.contains(data)) {
//                        newTransactionDataList.add(data)
//                    }
//                }
//            }
//        }
//        itemTransactionAdapter?.clear()
//        itemTransactionAdapter?.setList(newTransactionDataList)
//    }



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

    //set viewpager selection
    private fun setTabSelectedListner() {

        container!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlItemTransaction))

        tlItemTransaction!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                container!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }



    private fun setListAccordingSelectedPos(position: Int) {

    }



    fun getDate():String{
//        val builder = StringBuilder()
//        year=datePicker.year
//        month=datePicker.month+1
//        dayOfWeek=datePicker.dayOfMonth
//        builder.append((datePicker.year).toString() + "-") //month is 0 based
//        if(month<=9){
//            builder.append("0"+(month).toString() + "-")
//        }
//        else{
//            builder.append((datePicker.month+1).toString() + "-")
//        }
//        if(dayOfWeek<=9){
//            builder.append("0"+datePicker.dayOfMonth)
//        }
//        else{
//            builder.append(datePicker.dayOfMonth)
//        }
//        return builder.toString()
        return "abc"
    }

    override fun initObservers() {
        transactionViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(status: Any?) {
                list=status as ArrayList<TransactionData>
                Log.d("___@___","size in parent adapter"+list.size)
                adapter?.setList(list)
                adapter?.notifyDataSetChanged()
                itemTransactionAdapter?.setList(status as ArrayList<TransactionData>)
            }
        }))
    }


}