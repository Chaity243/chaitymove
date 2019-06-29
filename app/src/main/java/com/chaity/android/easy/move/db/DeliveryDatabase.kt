

package com.chaity.android.easy.move.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chaity.android.easy.move.appConverter.Converters
import com.chaity.android.easy.move.model.Delivery

/**
 * Database schema that holds the list of deliveries
 */

@TypeConverters(Converters::class)
@Database(
        entities = [Delivery::class],
        version = 1,
        exportSchema = false
)
abstract class DeliveryDatabase : RoomDatabase() {

    abstract fun deliveryDao(): DeliveryDao

}
