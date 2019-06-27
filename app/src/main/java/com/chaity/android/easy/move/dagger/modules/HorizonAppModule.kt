package com.chaity.android.easy.move.dagger.modules

import android.app.Application
import android.content.Context

import androidx.room.Room

import com.chaity.android.easy.move.HorizonMainApplication
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.db.DeliveryDao
import com.chaity.android.easy.move.db.DeliveryDatabase
import com.chaity.android.easy.move.db.DeliveryLocalCache


import java.util.concurrent.Executor
import java.util.concurrent.Executors

import javax.inject.Inject
import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
class HorizonAppModule {

    @Inject
    lateinit var mApplication: HorizonMainApplication

    @Singleton
    @Provides
    internal fun provideContext(application: HorizonMainApplication): Context {
        return application
    }


    @Singleton
    @Provides
    internal fun provideDeliveryRepository(service: DeliveryService, cache: DeliveryLocalCache): DeliveryRepository {
        return DeliveryRepository(service, cache)
    }


    @Singleton
    @Provides
    internal fun provideDeliveryLocalCache(deliveryDao: DeliveryDao, ioExecutor: Executor): DeliveryLocalCache {
        return DeliveryLocalCache(deliveryDao, ioExecutor)
    }


    @Singleton
    @Provides
    internal fun providesRoomDatabase(mApplication: HorizonMainApplication): DeliveryDatabase {
        this.mApplication = mApplication
        return Room.databaseBuilder(mApplication, DeliveryDatabase::class.java, "Delivery").build()
    }

    @Singleton
    @Provides
    internal fun providesProductDao(deliveryDatabase: DeliveryDatabase): DeliveryDao {
        return deliveryDatabase.deliveryDao()
    }

    @Singleton
    @Provides
    internal fun providesExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}
