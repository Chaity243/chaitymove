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

package com.chaity.android.easy.move.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import android.util.Log
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.api.searchRepos
import com.chaity.android.easy.move.db.DeliveryLocalCache

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class DeliveryBoundaryCallback(

        private val service: DeliveryService,
        private val cache: DeliveryLocalCache
) : PagedList.BoundaryCallback<Deliveries>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("DelBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Deliveries) {
        Log.d("DelBoundCallback", "onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(service, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage =lastRequestedPage+ NETWORK_PAGE_SIZE
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}