package com.chaity.android.easy.move.dagger.modules;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.chaity.android.easy.move.HorizonMainApplication;
import com.chaity.android.easy.move.api.DeliveryService;
import com.chaity.android.easy.move.data.DeliveryRepository;
import com.chaity.android.easy.move.db.DeliveryDao;
import com.chaity.android.easy.move.db.DeliveryDatabase;
import com.chaity.android.easy.move.db.DeliveryLocalCache;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class HorizonAppModule {

    @Inject
    HorizonMainApplication mApplication;
    @Singleton
    @Provides
    Context provideContext(HorizonMainApplication application){
        return application;
    }


    @Singleton
    @Provides
    DeliveryRepository provideDeliveryRepository(DeliveryService service, DeliveryLocalCache cache){
        return new DeliveryRepository(service, cache);
    }


    @Singleton
    @Provides
    DeliveryLocalCache provideDeliveryLocalCache(DeliveryDao deliveryDao, Executor ioExecutor){
        return new DeliveryLocalCache(deliveryDao, ioExecutor);
    }


    @Singleton
    @Provides
    DeliveryDatabase providesRoomDatabase(HorizonMainApplication mApplication) {
        this.mApplication=mApplication;
        return Room.databaseBuilder(mApplication, DeliveryDatabase.class, "Delivery").build();
    }

    @Singleton
    @Provides
    DeliveryDao providesProductDao(DeliveryDatabase deliveryDatabase) {
        return deliveryDatabase.deliveryDao();
    }

    @Singleton
    @Provides
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
