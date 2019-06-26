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

package com.chaity.android.easy.move.ui.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.model.DeliveriesResult

/**
 * ViewModel for the [DeliveriesActivity] screen.
 * The ViewModel works with the [DeliveryRepository] to get the data.
 */
class DeliveriesViewModel(private val repository: DeliveryRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<DeliveriesResult> = Transformations.map(queryLiveData) {
        repository.search()
    }

    val repos: LiveData<PagedList<Deliveries>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }
/*
    *//**
     * Get the last query value.
     *//*
    fun lastQueryValue(): String? = queryLiveData.value*/
}
