package com.app.kujacustomerapp.ui.dashboard.rfid

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.entity.request.account.ReIssueRequest
import com.app.kujacustomerapp.remote.entity.response.rfid.DeviceData
import com.app.kujacustomerapp.repository.dashboard.DashboardRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

class RfidViewModel @Inject constructor(application: Application,private val dashboardRepository: DashboardRepository) : RfidVariableViewModel(application) {
    var successLiveData=MutableLiveData<Event<ArrayList<DeviceData>>>()
    var successReOrderingRequestLiveData=MutableLiveData<Event<Boolean>>()
    var position:Int?=null
        get() = field
        set(position) {
            field = position
        }

    fun getDeviceData(){
        dashboardRepository.callGetDeviceData(3,object :Enqueue<ArrayList<DeviceData>>{
            override fun onSuccess(call: Call<*>, response: ArrayList<DeviceData>) {
                successLiveData.postValue(Event(response))
            }

            override fun onError(call: Call<*>, t: Throwable) {

            }
        })
    }

    fun setReOderingDevice(){
       dashboardRepository.callReOderingRfid(ReIssueRequest("0",position.toString(),"1","3"),object :Enqueue<Boolean>{
           override fun onSuccess(call: Call<*>, response: Boolean) {
               successReOrderingRequestLiveData.postValue(Event(response))
           }

           override fun onError(call: Call<*>, t: Throwable) {
               TODO("Not yet implemented")
           }
       })
    }



}