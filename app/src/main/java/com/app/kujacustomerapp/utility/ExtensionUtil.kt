package com.app.kujacustomerapp.utility

import android.content.Context
import android.widget.Toast
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
var  list=ArrayList<TransactionData>()
fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getList(): ArrayList<TransactionData>{
    return list
}

fun Context.setList(arrayList:ArrayList<TransactionData>){
   list=arrayList
}