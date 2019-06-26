package com.chaity.android.easy.move.ui.map

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.db.DeliveryDatabase
import com.chaity.android.easy.move.model.Deliveries
import java.security.AccessControlContext

class MapsViewModel (context: Application,id:Int):ViewModel()
{

    val deliveryItem :LiveData<Deliveries>


    init {

        val deliveryDao = DeliveryDatabase.getInstance(context).deliveryDao()
        deliveryItem=deliveryDao.getDeliveryItem(id)


    }
}