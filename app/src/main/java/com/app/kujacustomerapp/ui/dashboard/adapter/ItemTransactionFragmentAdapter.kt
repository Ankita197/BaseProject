package com.app.kujacustomerapp.ui.dashboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.DailyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.MonthlyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.WeeklyFragment
import com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment.YearlyFragment

class ItemTransactionFragmentAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int,
    private val newlist :ArrayList<TransactionData>
) : FragmentStatePagerAdapter(fm)  {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return YearlyFragment(newlist)
            }
            1 -> {
                return MonthlyFragment(newlist)

            }
            2 -> {
                // val movieFragment = MovieFragment()
                return WeeklyFragment()
            }
            3-> {
                // val movieFragment = MovieFragment()
                return DailyFragment(newlist)
            }
            else -> return YearlyFragment(newlist)
        }
    }

    override fun getCount(): Int {
       return totalTabs
    }
}