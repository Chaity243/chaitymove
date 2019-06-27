/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
