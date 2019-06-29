

package com.chaity.android.easy.move.ui.delivery

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chaity.android.easy.move.R
import com.chaity.android.easy.move.databinding.DeliveryViewItemBinding
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.ui.map.MapsActivity
import com.chaity.android.easy.move.utils.Constants.BUNDLE_KEY_DELIVERY
import com.squareup.picasso.Picasso

/**
 * View Holder for a [Delivery] RecyclerView list item.
 */
class DeliveryViewHolder(deliveryViewItemBinding: DeliveryViewItemBinding) : RecyclerView.ViewHolder(deliveryViewItemBinding.root) {
 private var deliveryViewItemBinding=deliveryViewItemBinding


 /*   private var deliveries: Deliveries? = null

    init {
        view.setOnClickListener {
            deliveries?.let { delivery ->
                val intent = Intent(view.context,MapsActivity::class.java).putExtra(BUNDLE_KEY_DELIVERY,delivery)
                view.context.startActivity(intent)
            }
        }
    }
*/
  /*  fun bind(delivery: Deliveries?) {
        if (delivery == null) {
            val resources = itemView.resources
            tv_del.text = resources.getString(R.string.loading)
            iv_del.visibility = View.GONE

        } else {
            showDeliveryData(delivery)
        }
    }*/
/*

    private fun showDeliveryData(delivery: Deliveries) {
        this.deliveries = delivery
        tv_del.text = delivery.description

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (delivery.description != null) {
            tv_del.text = delivery.description
            descriptionVisibility = View.VISIBLE
        }
        tv_del.visibility = descriptionVisibility



        // if the image is missing, hide the label and the value
        var imageVisibility = View.GONE
        if (!deliveries!!.imageUrl.isEmpty()) {
            Picasso.get()
                    .load(deliveries!!.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_eye)
                    .fit()
                    .into(iv_del)

            imageVisibility = View.VISIBLE
        }
        iv_del.visibility = imageVisibility
    }
*/



    fun bind(model :DeliveryAdapterViewModel)
    {
        deliveryViewItemBinding.viewModel=model
    }

}
