package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentDashboardBinding
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import kotlinx.android.synthetic.main.fragment_daily.*


class DailyFragment : BaseBindingFragment<FragmentDashboardBinding>() {

    var year:Int=0
    var month:Int=0
    var dayOfWeek:Int=0


    override val contentView: Int
        get() = R.layout.fragment_daily

    override fun viewModelSetup() {
    }

    override fun initViews() {

    }

    override fun initObservers() {
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