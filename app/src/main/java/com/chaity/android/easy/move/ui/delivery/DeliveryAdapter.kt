

package com.chaity.android.easy.move.ui.delivery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaity.android.easy.move.R
import com.chaity.android.easy.move.model.Deliveries

/**
 * Adapter for the list of deliveries.
 */
class DeliveryAdapter : PagedListAdapter <Deliveries, RecyclerView.ViewHolder>(DELIVERY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.delivery_view_item, parent, false)
        return DeliveryViewHolder(view)

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
