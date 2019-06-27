

package com.chaity.android.easy.move.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import android.util.Log
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.api.searchRepos
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.utils.Constants

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class DeliveryBoundaryCallback(

        private val service: DeliveryService,
        private val cache: DeliveryLocalCache
) : PagedList.BoundaryCallback<Deliveries>() {


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
        searchRepos(service, lastRequestedPage,  Constants.API_LIST_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage =lastRequestedPage+  Constants.API_LIST_SIZE
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}