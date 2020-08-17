package com.app.kujacustomerapp.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.RowItemImageBinding

class GridAdapter constructor(
    private val mContext: Context,
    private val imgList: List<String>
) : RecyclerView.Adapter<GridAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): GridAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding: RowItemImageBinding =
            DataBindingUtil.inflate(inflater, R.layout.row_item_image, viewGroup, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GridAdapter.ItemViewHolder,
        position: Int
    ) {
        val imageUrl = imgList[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    inner class ItemViewHolder(private val binding: RowItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imgUrl: String?) {
            binding.url = imgUrl
        }

    }

}