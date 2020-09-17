package com.app.kujacustomerapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.RowItemImageBinding
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData
import kotlinx.android.synthetic.main.item_rfid.view.*

class ItemRfidAdapter : RecyclerView.Adapter<ItemRfidAdapter.ItemViewHolder>(){

    var listItemRfid=ArrayList<DeviceData>()

    fun setList(list: ArrayList<DeviceData>){
        this.listItemRfid=list
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val item=    LayoutInflater.from(parent.context).inflate( R.layout.item_rfid, parent, false)
        return ItemRfidAdapter.ItemViewHolder(item)
    }

    override fun getItemCount(): Int {
        return listItemRfid.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.tvDeviceType.text=getDeviceType(listItemRfid[position].rfidDeviceType)
        holder.itemView.tvRfidNumber.text= listItemRfid[position].rfidNumber
        holder.itemView.btnState.text=getDeviceStatus(listItemRfid[position].status)
    }

    private fun getDeviceStatus(status: Int?): CharSequence? {
        if(status==1)
            return "Active"
        if (status==2)
            return "InActive"
        return "InActive"
    }

    private fun getDeviceType(rfidDeviceType: Int?): CharSequence? {
        if(rfidDeviceType==1)
            return "Card"
        if (rfidDeviceType==2)
            return "bend"
        return "Card"
    }


}