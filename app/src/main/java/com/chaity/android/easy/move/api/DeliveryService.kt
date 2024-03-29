/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chaity.android.easy.move.api

import android.util.Log
import com.chaity.android.easy.move.model.Deliveries
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val TAG = "DeliveryService"
private const val IN_QUALIFIER = "in:name,description"

/**
 * Search repos based on a query.
 * Trigger a request to the Github searchRepo API with the following params:
 * @param query searchRepo keyword
 * @param page request page index
 * @param itemsPerPage number of repositories to be returned by the Github API per page
 *
 * The result of the request is handled by the implementation of the functions passed as params
 * @param onSuccess function that defines how to handle the list of repos received
 * @param onError function that defines how to handle request failure
 */
fun searchRepos(
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

/**
 * Github API communication setup via Retrofit.
 */
interface DeliveryService {
    /**
     * Get repos ordered by stars.
     */
    @GET("/deliveries")
    fun searchRepos(
            @Query("offset") lastRequestedPage: Int,
        @Query("limit") itemsPerPage: Int

    ): Call<List<Deliveries>>

    companion object {
        private const val BASE_URL = "https://mock-api-mobile.dev.lalamove.com"

        fun create(): DeliveryService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DeliveryService::class.java)
        }
    }
}