package com.chaity.android.easy.move.ui.delivery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaity.android.easy.move.databinding.DeliveryViewItemBinding
import com.chaity.android.easy.move.listener.DeliveryItemClickListener
import com.chaity.android.easy.move.model.Delivery

/**
 * Adapter for the list of delivery Items
 */
class DeliveryAdapter (deliveryItemClickListener : DeliveryItemClickListener): PagedListAdapter <Delivery, RecyclerView.ViewHolder>(DELIVERY_COMPARATOR) {


    lateinit var inflater: LayoutInflater
    val deliveryItemClickListener=deliveryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        inflater= LayoutInflater.from(parent.context)

        var  dataBinding = DeliveryViewItemBinding.inflate(inflater, parent, false)
        return DeliveryViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            var deliveryItem  =  DeliveryAdapterViewModel(repoItem.id
                    ,repoItem.description,repoItem.imageUrl)
            (holder as DeliveryViewHolder).bind(deliveryItem)


            holder.itemView.setOnClickListener()
            {
                deliveryItemClickListener.onItemClicked(repoItem)
            }

        }
    }

    companion object {
        private val DELIVERY_COMPARATOR = object : DiffUtil.ItemCallback<Delivery>() {
            override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean =
                    oldItem == newItem
        }
    }
}
