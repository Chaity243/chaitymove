package com.chaity.android.easy.move.data

import android.util.Log
import com.chaity.android.easy.move.api.DeliveryAPI
import com.chaity.android.easy.move.model.Delivery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DeliveryAPI"

fun getDeliveriesFRomService(
        api: DeliveryAPI,
        lastRequestedPage:Int,
        itemsPerPage: Int,
        onSuccess: (repos: List<Delivery>) -> Unit,
        onError: (error: String) -> Unit
) {
    Log.d(TAG, "itemsPerPage: $itemsPerPage")



    api.deliveryAPI( lastRequestedPage,itemsPerPage).enqueue(
            object : Callback<List<Delivery>> {
                override fun onFailure(call: Call<List<Delivery>>?, t: Throwable) {
                    Log.d(TAG, "fail to get data")
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<List<Delivery>>?,
                        response: Response<List<Delivery>>
                ) {
                    Log.d(TAG, "got a response $response")
                    if (response.isSuccessful) {
                        val repos = response.body()?: emptyList()
                        onSuccess(repos)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}