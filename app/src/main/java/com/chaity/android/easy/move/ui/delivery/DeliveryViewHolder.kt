package com.chaity.android.easy.move.ui.delivery

import androidx.recyclerview.widget.RecyclerView
import com.chaity.android.easy.move.databinding.DeliveryViewItemBinding

/**
 * View Holder for a [Delivery] RecyclerView list item.
 */
class DeliveryViewHolder(deliveryViewItemBinding: DeliveryViewItemBinding) : RecyclerView.ViewHolder(deliveryViewItemBinding.root) {
    private var deliveryViewItemBinding=deliveryViewItemBinding

    fun bind(model :DeliveryAdapterViewModel)
    {
        deliveryViewItemBinding.viewModel=model
    }

}
