package com.app.kujacustomerapp.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.RowItemUserBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData.User

class ImageListAdapter(private val context: Context, private var userlist: List<User>) :
    RecyclerView.Adapter<ImageListAdapter.ItemViewHolder>() {

    private var gridPostAdapter: GridAdapter? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding: RowItemUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.row_item_user, viewGroup, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val usersModel = userlist[position]
        holder.bind(usersModel)
    }

    private fun setPostDataAdapter(
        items: List<String>?,
        rvPost: RecyclerView
    ) {
        if (items != null && items.isNotEmpty()) {
            val gridLayoutManager = GridLayoutManager(context, 2)
            gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (items.size % 2 == 0) 1 else if (position == 0) 2 else 1
                }
            }
            gridPostAdapter = GridAdapter(context, items)
            rvPost.layoutManager = gridLayoutManager
            rvPost.adapter = gridPostAdapter
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    fun setData(newList: List<User>) {
        userlist = newList
    }

    inner class ItemViewHolder(private val binding: RowItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: DashboardData.User) {
            binding.user = user
            setPostDataAdapter(user.items, binding.rvPost)
        }
    }

}
