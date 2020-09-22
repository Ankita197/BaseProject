package com.app.kujacustomerapp.ui.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.RowItemImageBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import kotlinx.android.synthetic.main.item_rfid.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemTransactionAdapter(): RecyclerView.Adapter<ItemTransactionAdapter.ItemViewHolder>() {
    private var list= ArrayList<TransactionData>()
    private var onItemTransactionClick:OnItemTransactionClick?=null
    class ItemViewHolder( var binding: RowItemImageBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(transactionModel: TransactionData) {
            binding.transactionData=transactionModel
            binding.tvBusinessName.text = transactionModel.businessName
            if(transactionModel.businessName.isNullOrEmpty()) {
                binding.tvBusinessName.text = "PQR pharma"
            }
            binding.tvDate.text= transactionModel.trxDate?.let { setDate(it) }
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

    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }

    fun setList(list: ArrayList<TransactionData>){
       this.list=list
        notifyDataSetChanged()
    }

    fun setItemClick(onItemTransactionClick: OnItemTransactionClick){
       this.onItemTransactionClick=onItemTransactionClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RowItemImageBinding =
            DataBindingUtil.inflate(inflater, R.layout.row_item_image, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transactionModel = list[position]
        holder.bind(transactionModel)
        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemTransactionClick?.onItemClick(position,holder)
        })
        holder.itemView.btnState.setOnClickListener(View.OnClickListener {
            onItemTransactionClick?.onItemClick(position,holder)
        })
    }
}