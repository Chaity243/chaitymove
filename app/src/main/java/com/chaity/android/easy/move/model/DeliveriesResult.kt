

package com.chaity.android.easy.move.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Delivery API Result, which contains LiveData<List<Delivery>> holding delivery items list data,
 * and a LiveData<String> of network error state.
 */
data class DeliveriesResult(
        val data: LiveData<PagedList<Delivery>>,
        val networkErrors: LiveData<String>
)
