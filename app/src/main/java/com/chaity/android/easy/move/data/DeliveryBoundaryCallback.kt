package com.chaity.android.easy.move.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.chaity.android.easy.move.BuildConfig.API_LIST_SIZE
import com.chaity.android.easy.move.api.DeliveryAPI
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.model.Delivery
import com.chaity.android.easy.move.utils.Constants

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class DeliveryBoundaryCallback(

        private val api: DeliveryAPI,
        private val cache: DeliveryLocalCache
) : PagedList.BoundaryCallback<Delivery>() {


    // keep the last requested no of items i.e. offset . When the request is successful, increment the  offset .
    private var offset = 0

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    var isRequestInProgress:  MutableLiveData<Boolean>  = MutableLiveData()

    init{
        isRequestInProgress.postValue(false)
    }

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("DelBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData()
    }

    /**
     * When all items from the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        Log.d("DelBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress.value!!) return

        isRequestInProgress.postValue(true)
        getDeliveriesFRomService(api, offset,API_LIST_SIZE, { repos ->
            cache.insert(repos) {
                offset =offset+  API_LIST_SIZE
                isRequestInProgress.postValue(false)
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress.postValue(false)
        })
    }
}