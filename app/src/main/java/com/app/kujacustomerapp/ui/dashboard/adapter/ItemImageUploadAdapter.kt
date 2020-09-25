package com.app.kujacustomerapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import kotlinx.android.synthetic.main.row_item_image_store.view.*

class ItemImageUploadAdapter: RecyclerView.Adapter<ItemImageUploadAdapter.ItemViewHolder>() {

  private  var imageArrayList=ArrayList<String>()
    private var onItemTransactionClick:OnItemTransactionClick?=null

    fun setList(imageArrayList:ArrayList<String>){
        this.imageArrayList=imageArrayList
        notifyDataSetChanged()
    }

    fun setClick(onItemTransactionClick:OnItemTransactionClick){
        this.onItemTransactionClick=onItemTransactionClick
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemImageUploadAdapter.ItemViewHolder {
        val item=    LayoutInflater.from(parent.context).inflate( R.layout.row_item_image_store, parent, false)
        return ItemImageUploadAdapter.ItemViewHolder(item)
    }

    override fun getItemCount(): Int {
       return imageArrayList.size
    }

    override fun onBindViewHolder(holder: ItemImageUploadAdapter.ItemViewHolder, position: Int) {
            holder.itemView.tvUploadDocument.text=imageArrayList[position]
        holder.itemView.ivDelete.setOnClickListener(View.OnClickListener {
            onItemTransactionClick?.onItemClick(position,holder)
        })
    }
}