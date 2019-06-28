package com.chaity.android.easy.move.ui.map

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.db.DeliveryDatabase
import com.chaity.android.easy.move.model.Deliveries
import com.chaity.android.easy.move.ui.delivery.DeliveryAdapter
import java.security.AccessControlContext
import javax.inject.Inject

class MapsViewModel (context: Application,id:Int):ViewModel()
{

    val deliveryItem :LiveData<Deliveries>

    @Inject lateinit var daliveryDatabase: DeliveryDatabase


    init {

        val deliveryDao = daliveryDatabase.deliveryDao()
        deliveryItem=deliveryDao.getDeliveryItem(id)
    }
}