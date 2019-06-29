

package com.chaity.android.easy.move.db

import android.util.Log
import androidx.paging.DataSource
import com.chaity.android.easy.move.model.Delivery
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */

class DeliveryLocalCache  @Inject constructor (
        private val deliveryDao: DeliveryDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of deliveries in the database, on a background thread.
     */
    fun insert(repos: List<Delivery>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("DeliveryLocalCache", "inserting ${repos.size} repos")
            deliveryDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Delivery>> from the Dao.
     */
    fun getAllDeliveries(): DataSource.Factory<Int, Delivery> {

        return deliveryDao.getAllDeliveries()
    }
}
