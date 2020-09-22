package com.app.kujacustomerapp.ui.dashboard.transaction_history

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentTransactionHistoryBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionFragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import javax.inject.Inject


open class TransactionHistoryFragment() : BaseBindingFragment<FragmentTransactionHistoryBinding>(){



    var list= ArrayList<TransactionData>()
    var adapter:ItemTransactionFragmentAdapter?=null


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var transactionViewModel: TransactionViewModel? = null
    override val contentView: Int
        get() = R.layout.fragment_transaction_history

    override fun viewModelSetup() {
        transactionViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(TransactionViewModel::class.java)
    }

    override fun initViews() {
        setTabsItem()
        transactionViewModel?.getTransactionData()
        setTabSelectedListner()
        setButtonClick()
    }

    private fun setButtonClick() {
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





    //set viewpager selection
    private fun setTabSelectedListner() {

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlItemTransaction))

        tlItemTransaction!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                container.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    override fun initObservers() {
        transactionViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(status: Any?) {
                list=status as ArrayList<TransactionData>
                adapter = activity?.let { ItemTransactionFragmentAdapter(it,childFragmentManager, tlItemTransaction.tabCount,list) }
                container.adapter = adapter

            }
        }))
    }


}