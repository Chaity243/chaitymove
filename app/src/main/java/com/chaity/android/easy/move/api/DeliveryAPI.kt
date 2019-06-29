package com.chaity.android.easy.move.api

import com.chaity.android.easy.move.model.Delivery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * get delivery list
 * Trigger a request to Delivery API with the following params:
 * @param offset starting index
 * @param limit items per request

 */


/**
 * get delivery list  API communication setup via Retrofit.
 */
interface DeliveryAPI {
    /**
     * Get delivery list
     */
    @GET("/deliveries")
    fun deliveryAPI(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int

    ): Call<List<Delivery>>


}