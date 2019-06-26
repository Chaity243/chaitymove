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

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaity.android.easy.move.model.Deliveries

/**
 * Adapter for the list of deliveries.
 */
class DeliveryAdapter : PagedListAdapter<Deliveries, RecyclerView.ViewHolder>(DELIVERY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DeliveryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as DeliveryViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val DELIVERY_COMPARATOR = object : DiffUtil.ItemCallback<Deliveries>() {
            override fun areItemsTheSame(oldItem: Deliveries, newItem: Deliveries): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Deliveries, newItem: Deliveries): Boolean =
                    oldItem == newItem
        }
    }
}
