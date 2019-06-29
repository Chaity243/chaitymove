package com.chaity.android.easy.move.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.model.DeliveriesResult
import com.chaity.android.easy.move.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject





/**
 * Repository class that works with local and remote data sources.
 */
class DeliveryRepository   @Inject constructor (
        private val service: DeliveryService,
        private val cache: DeliveryLocalCache
) {

    /**
     * get all deliveries
     */
    fun getDeliveries(): DeliveriesResult {


        // Get data source factory from the local cache
        val dataSourceFactory = cache.getAllDeliveries()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = DeliveryBoundaryCallback( service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, Constants.API_LIST_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return DeliveriesResult(data, networkErrors)
    }



}
