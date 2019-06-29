package com.chaity.android.easy.move.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.chaity.android.easy.move.BuildConfig.API_LIST_SIZE
import com.chaity.android.easy.move.api.DeliveryAPI
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.model.DeliveriesResult
import com.chaity.android.easy.move.utils.Constants
import javax.inject.Inject





/**
 * Repository class that works with local and remote data sources.
 */
class DeliveryRepository   @Inject constructor (
        private val api: DeliveryAPI,
        private val cache: DeliveryLocalCache
) {

    /**
     * get all deliveries
     */

    var  loader=MutableLiveData<Boolean>()
    fun getDeliveries(): DeliveriesResult {


        // Get data source factory from the local cache
        val dataSourceFactory = cache.getAllDeliveries()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = DeliveryBoundaryCallback( api, cache)
        val networkErrors = boundaryCallback.networkErrors
        loader=boundaryCallback.isRequestInProgress

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory,API_LIST_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return DeliveriesResult(data, networkErrors)
    }



}
