package com.app.kujacustomerapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.RowItemImageBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData

class ItemTransactionAdapter(): RecyclerView.Adapter<ItemTransactionAdapter.ItemViewHolder>() {
    var list= mutableListOf<TransactionData>()
    class ItemViewHolder( var binding: RowItemImageBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(transactionModel: TransactionData) {
            binding.transactionData=transactionModel
        }

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
    }
}