

package com.chaity.android.easy.move.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters
import com.chaity.android.easy.move.appConverter.Converters
import com.chaity.android.easy.move.model.Deliveries

/**
 * Database schema that holds the list of repos.
 */

@TypeConverters(Converters::class)
@Database(
        entities = [Deliveries::class],
        version = 1,
        exportSchema = false
)
abstract class DeliveryDatabase : RoomDatabase() {

    abstract fun deliveryDao(): DeliveryDao

    companion object {

        @Volatile
        private var INSTANCE: DeliveryDatabase? = null

        fun getInstance(context: Context): DeliveryDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        DeliveryDatabase::class.java, "Delivery.db")
                        .build()
    }
}
