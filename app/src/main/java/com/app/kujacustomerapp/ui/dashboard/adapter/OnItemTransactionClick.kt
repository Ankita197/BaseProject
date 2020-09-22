package com.app.kujacustomerapp.ui.dashboard.adapter

import androidx.recyclerview.widget.RecyclerView

interface OnItemTransactionClick {

    fun onItemClick(pos:Int,holder: RecyclerView.ViewHolder)
}