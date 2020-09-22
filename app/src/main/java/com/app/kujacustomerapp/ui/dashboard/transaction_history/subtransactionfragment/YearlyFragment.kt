package com.app.kujacustomerapp.ui.dashboard.transaction_history.subtransactionfragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentYearlyBinding
import com.app.kujacustomerapp.persistance.DashboardSharedPrefs
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.dashboard.adapter.ItemTransactionAdapter
import com.app.kujacustomerapp.ui.dashboard.adapter.OnItemTransactionClick
import com.app.kujacustomerapp.ui.dashboard.transaction_history.TransactionViewModel
import kotlinx.android.synthetic.main.dialog_success.view.*
import kotlinx.android.synthetic.main.dialog_success.view.btnOk
import kotlinx.android.synthetic.main.dialog_transaction_history.view.*
import kotlinx.android.synthetic.main.dialog_transfer_money.view.*
import kotlinx.android.synthetic.main.dialog_transfer_money.view.tvTransactionIdValue
import kotlinx.android.synthetic.main.fragment_yearly.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class YearlyFragment(val newlist: ArrayList<TransactionData>) :
    BaseBindingFragment<FragmentYearlyBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var alert: AlertDialog? = null
    var customLayout: View? = null

    @Inject
    lateinit var dashboardSharedPrefs: DashboardSharedPrefs
    var itemTransactionAdapter: ItemTransactionAdapter? = null
    var seletedYear: Int = 0;
    var list = ArrayList<TransactionData>()
    var filteredList :List<TransactionData>?=null


    override val contentView: Int
        get() = R.layout.fragment_yearly

    override fun viewModelSetup() {

    }

    override fun initViews() {
        list = newlist
        itemTransactionAdapter = ItemTransactionAdapter()
        itemTransactionAdapter?.setList(list)
        setNumberPicker()
        rvItemTransaction.adapter = itemTransactionAdapter
        setItemClick()
        setDialog()
        btnViewTransaction.setOnClickListener(View.OnClickListener {
            filteredList=null
            filteredList=list.filter { it.trxDate?.let { it1 -> setDate(it1)?.let { it1 -> getYear(it1) } } ==seletedYear}
            if (filteredList!=null) {
                itemTransactionAdapter?.setList(filteredList as ArrayList<TransactionData>)
            }
        })
    }

    private fun setDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        customLayout = layoutInflater.inflate(R.layout.dialog_transaction_history, null)
        alertDialog.setView(customLayout)
        customLayout?.rootView?.btnOk?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                alert?.dismiss()
            }
        })

        alert = alertDialog.create()
        alert?.setCanceledOnTouchOutside(false)
    }

    private fun setItemClick() {
        itemTransactionAdapter?.setItemClick(object : OnItemTransactionClick {
            override fun onItemClick(pos: Int, holder: RecyclerView.ViewHolder) {
                showDialog(pos)
            }
        })
    }

    private fun showDialog(pos: Int) {
        customLayout?.rootView?.tvTransactionIdValue?.text = list[pos].customerTrxID.toString()
        customLayout?.rootView?.tvMerchantIdValue?.text = list[pos].merchantID.toString()
        customLayout?.rootView?.tvSenderPhoneNoValue?.text = list[pos].senderPhoneNumber.toString()
        customLayout?.rootView?.tvReceiverPhoneNoValue?.text =
            list[pos].receiverPhoneNumber.toString()
        customLayout?.rootView?.btnStatus?.text = getStatus(list[pos].status)
        customLayout?.rootView?.tvDateValue?.text = list[pos].createdAt?.let { setDate(it) }
        customLayout?.rootView?.tvBusinessNameValue?.text = list[pos].businessName
        customLayout?.rootView?.tvTimeValue?.text = list[pos].createdAt?.let { setTime(it) }
        customLayout?.rootView?.tvTransactionAmount?.text = list[pos].amount?.toInt().toString()
        alert?.show()
    }

    private fun getStatus(status: Int?): CharSequence? {
        if (status == 1) {
            return "Success"
        } else {
            return "Pending"
        }
    }

    private fun setNumberPicker() {
        numberPicker.maxValue = 120
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = (true);
        val nums = arrayOfNulls<String>(121)
        for (i in nums.indices) nums[i] = (i + 1900).toString()
        numberPicker.displayedValues = nums;
        numberPicker.value = 120;
        seletedYear = nums[120]?.let { Integer.parseInt(it) }!!
        numberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { numberPicker, i, i1 ->
            val valuePicker1: Int = numberPicker.value
            seletedYear = nums[valuePicker1]?.let { Integer.parseInt(it) }!!
        })
    }

    override fun initObservers() {

    }

    private fun setDate(date: String): String? {
        var reformattedStr = ""
        @SuppressLint("SimpleDateFormat") val fromUser =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        @SuppressLint("SimpleDateFormat") val myFormat =
            SimpleDateFormat("yyyy-MM-dd")
        try {
            reformattedStr =
                myFormat.format(Objects.requireNonNull(fromUser.parse(date)))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return reformattedStr
    }

    private fun setTime(date: String): String? {
        var reformattedStr = ""
        @SuppressLint("SimpleDateFormat") val fromUser =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        @SuppressLint("SimpleDateFormat") val myFormat =
            SimpleDateFormat("HH:mm")
        try {
            reformattedStr =
                myFormat.format(Objects.requireNonNull(fromUser.parse(date)))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return reformattedStr
    }

    private fun getYear(dot:String):Int?{

        var yer: Int? = 0
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = sdf.parse(dot)
            val cal = Calendar.getInstance()
            cal.time = d

            yer = (cal[Calendar.YEAR])

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return yer
    }
}