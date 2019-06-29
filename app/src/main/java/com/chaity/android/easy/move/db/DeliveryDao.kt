package com.chaity.android.easy.move.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chaity.android.easy.move.model.Delivery

/**
 * Room data access object for accessing the [Delivery] table.
 */
@Dao

interface DeliveryDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Delivery>)

  // Get All Delivery Items
    @Query("SELECT * FROM Delivery ")
    fun getAllDeliveries(): DataSource.Factory<Int, Delivery>


  // Get delivery item against specified id.
    @Query("SELECT * FROM Delivery where id =:id")
    fun getDeliveryItem(id:Int): LiveData<Delivery>
}
