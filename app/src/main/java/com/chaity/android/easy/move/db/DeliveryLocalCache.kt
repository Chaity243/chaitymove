

package com.chaity.android.easy.move.db

import android.util.Log
import androidx.paging.DataSource
import com.chaity.android.easy.move.model.Deliveries
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
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<Deliveries>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("DeliveryLocalCache", "inserting ${repos.size} repos")
            deliveryDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a repo name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun getAllDeliveries(): DataSource.Factory<Int, Deliveries> {

        return deliveryDao.getAllDeliveries()
    }
}
