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

import androidx.paging.LivePagedListBuilder
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.model.DeliveriesResult
import javax.inject.Inject

/**
 * Repository class that works with local and remote data sources.
 */
class DeliveryRepository   @Inject constructor (
        private val service: DeliveryService,
        private val cache: DeliveryLocalCache
) {

    /**
     * get all deliveries or
     * Search repositories whose names match the query.
     * we can pass query parameter as an extension to code for including search functionality
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
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return DeliveriesResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
