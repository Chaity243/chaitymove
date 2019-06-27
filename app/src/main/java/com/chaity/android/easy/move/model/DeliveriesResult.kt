

package com.chaity.android.easy.move.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * DeliveriesResult from a search, which contains LiveData<List<Repo>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class DeliveriesResult(
        val data: LiveData<PagedList<Deliveries>>,
        val networkErrors: LiveData<String>
)
