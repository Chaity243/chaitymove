package com.chaity.android.easy.move.data

import android.util.Log
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.model.Deliveries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DeliveryService"

fun getDeliveriesFRomService(
        service: DeliveryService,
        lastRequestedPage:Int,
        itemsPerPage: Int,
        onSuccess: (repos: List<Deliveries>) -> Unit,
        onError: (error: String) -> Unit
) {
    Log.d(TAG, "itemsPerPage: $itemsPerPage")



    service.searchRepos( lastRequestedPage,itemsPerPage).enqueue(
            object : Callback<List<Deliveries>> {
                override fun onFailure(call: Call<List<Deliveries>>?, t: Throwable) {
                    Log.d(TAG, "fail to get data")
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<List<Deliveries>>?,
                        response: Response<List<Deliveries>>
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