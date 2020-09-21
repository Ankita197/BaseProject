package com.app.kujacustomerapp.remote.entity.request.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MakePaymentRequest (  @SerializedName("SenderPhoneNumber")
                                 @Expose
                                 var senderPhoneNumber: String? = null,
                                 @SerializedName("Amount")
                                 @Expose
                                 var amount: String? = null,

                                 @SerializedName("ReceiverPhoneNumber")
                                 @Expose
                                 var receiverPhoneNumber: String? = null,

                                 @SerializedName("PayerMessage")
                                 @Expose
                                 var payerMessage: String? = null)
