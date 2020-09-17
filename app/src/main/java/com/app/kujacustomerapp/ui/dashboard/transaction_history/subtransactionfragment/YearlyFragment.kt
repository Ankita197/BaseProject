package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentYearlyBinding
import com.app.kujacustomerapp.persistance.DashboardSharedPrefs
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionViewModel
import com.app.kujacustomerapp.utility.setList
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import kotlinx.android.synthetic.main.fragment_yearly.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class YearlyFragment : BaseBindingFragment<FragmentYearlyBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var dashboardSharedPrefs: DashboardSharedPrefs
    var itemTransactionAdapter: ItemTransactionAdapter?=null
    var listOfDate= ArrayList<String>()
    var transactionViewModel: TransactionViewModel? = null
    var seletedYear:Int=0;

    companion object {
        var list= ArrayList<TransactionData>()
    }

    override val contentView: Int
        get() = R.layout.fragment_yearly

    override fun viewModelSetup() {
        transactionViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(TransactionViewModel::class.java)
    }

    override fun initViews() {
        transactionViewModel?.getTransactionData()
        itemTransactionAdapter= ItemTransactionAdapter()
        setNumberPicker()
        rvItemTransaction.adapter=itemTransactionAdapter
    }

    private fun setNumberPicker() {
        numberPicker.maxValue=120
        numberPicker.minValue=0
        numberPicker.wrapSelectorWheel=(true);
        val nums = arrayOfNulls<String>(121)
        for (i in nums.indices) nums[i] = (i + 1900).toString()
        numberPicker.displayedValues = nums;
        numberPicker.value = 120;
        seletedYear= nums[120]?.let { Integer.parseInt(it) }!!
        numberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { numberPicker, i, i1 ->
            val valuePicker1: Int = numberPicker.value
            seletedYear = nums[valuePicker1]?.let { Integer.parseInt(it) }!!
        })
    }

    override fun initObservers() {
        transactionViewModel?.successLiveData?.observe(this, EventObserver<Any>(object :
            OnEventUnhandledContent {
            override fun onChanged(status: Any?) {

                list=status as ArrayList<TransactionData>
                activity?.setList(list)
                itemTransactionAdapter?.setList(status as ArrayList<TransactionData>)
                for(data in list) {
                    data.trxDate?.let { setDate(it)?.let { listOfDate.add(it) } }
                }
            }
        }))
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

}