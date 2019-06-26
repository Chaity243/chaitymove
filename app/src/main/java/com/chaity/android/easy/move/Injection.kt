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

package com.chaity.android.easy.move

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.db.DeliveryLocalCache
import com.chaity.android.easy.move.db.DeliveryDatabase
import com.chaity.android.easy.move.ui.delivery.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [DeliveryLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): DeliveryLocalCache {
        val database = DeliveryDatabase.getInstance(context)
        return DeliveryLocalCache(database.deliveryDao(), Executors.newSingleThreadExecutor())
    }



    /**
     * Creates an instance of [DeliveryRepository] based on the [DeliveryService] and a
     * [DeliveryLocalCache]
     */
    private fun provideDeliveryRepository(context: Context): DeliveryRepository {
        return DeliveryRepository(DeliveryService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideDeliveryRepository(context))
    }
}
