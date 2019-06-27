

package com.chaity.android.easy.move.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chaity.android.easy.move.model.Deliveries
import javax.inject.Inject

/**
 * Room data access object for accessing the [Repo] table.
 */
@Dao

interface DeliveryDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Deliveries>)

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM Deliveries ")
    fun getAllDeliveries(): DataSource.Factory<Int, Deliveries>

    @Query("SELECT * FROM Deliveries where id =:id")
    fun getDeliveryItem(id:Int): LiveData<Deliveries>
}
