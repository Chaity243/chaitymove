package com.chaity.android.easy.move.dagger.modules

import androidx.room.Room
import com.chaity.android.easy.move.MyApp
import com.chaity.android.easy.move.db.DeliveryDao
import com.chaity.android.easy.move.db.DeliveryDatabase
import com.chaity.android.easy.move.db.DeliveryLocalCache
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class DatabaseModule{

    @Singleton
    @Provides
    internal fun providesDeliveryDatabase(mApplication: MyApp): DeliveryDatabase {
        return Room.databaseBuilder(mApplication, DeliveryDatabase::class.java, "Delivery").build()
    }


    @Singleton
    @Provides
    internal fun providesDeliveryDao(deliveryDatabase: DeliveryDatabase): DeliveryDao {
        return deliveryDatabase.deliveryDao()
    }

    @Singleton
    @Provides
    internal fun provideDeliveryLocalCache(deliveryDao: DeliveryDao, ioExecutor: Executor): DeliveryLocalCache {
        return DeliveryLocalCache(deliveryDao, ioExecutor)
    }
}