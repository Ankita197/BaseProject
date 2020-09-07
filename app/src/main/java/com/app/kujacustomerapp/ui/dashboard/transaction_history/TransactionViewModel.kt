package com.app.kujacustomerapp.ui.dashboard.transaction_history

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.app.kujacustomerapp.BR
import com.app.kujacustomerapp.interfaces.Enqueue
import com.app.kujacustomerapp.remote.base.exception.AppHttpException
import com.app.kujacustomerapp.remote.entity.response.dashboard.TransactionData
import com.app.kujacustomerapp.repository.dashboard.DashboardDataRepository
import com.app.kujacustomerapp.ui.base.event.Event
import retrofit2.Call
import javax.inject.Inject

class TransactionViewModel @Inject constructor(application: Application,private val dashboardDataRepository: DashboardDataRepository) :TransactionVariableViewModel(application){

    var successLiveData: MutableLiveData<Event<ArrayList<TransactionData>>> =
        MutableLiveData()

    var errorLiveData = MutableLiveData<Event<String?>>()

    var startDate:String =""
        @Bindable get() = field
        set(startDate) {
            field = startDate
        }

    var endDate:String =""
        @Bindable get() = field
        set(endDate) {
            field = endDate
        }

    fun getTransactionData(){


    dashboardDataRepository.callGetAllTransactionData(1,startDate,endDate,object :
        Enqueue<ArrayList<TransactionData>>{
        override fun onSuccess(call: Call<*>, response: ArrayList<TransactionData>) {
            successLiveData.postValue(
                Event(
                response
            )
            )
        }

        override fun onError(call: Call<*>, t: Throwable) {
            if (t is AppHttpException) {
                errorLiveData.postValue(
                    Event(
                        t.errorResponse.message
                    )
                )
            } else {
                errorLiveData.postValue(
                    Event(
                        t!!.localizedMessage
                    )
                )
            }
        }

    })
}



}