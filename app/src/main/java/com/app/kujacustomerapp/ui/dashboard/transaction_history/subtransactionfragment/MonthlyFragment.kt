package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.util.Log
import android.view.View
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentMonthlyBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.kal.rackmonthpicker.RackMonthPicker
import kotlinx.android.synthetic.main.fragment_monthly.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MonthlyFragment(var list: ArrayList<TransactionData>) : BaseBindingFragment<FragmentMonthlyBinding>() {

    var selectedMonth:Int=0
    var monthlyDataList= ArrayList<TransactionData>()
    var newTransactionDataList= ArrayList<TransactionData>()
    var filteredList= listOf<String>()

    override val contentView: Int
        get() = R.layout.fragment_monthly


    override fun viewModelSetup() {
    }

    override fun initViews() {
        monthlyDataList=list
        Log.d("___@___","size is"+ monthlyDataList)
        tvSelectMonth.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                showMonthSelectDialog()
            }

        })
        btnViewTransaction.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })
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