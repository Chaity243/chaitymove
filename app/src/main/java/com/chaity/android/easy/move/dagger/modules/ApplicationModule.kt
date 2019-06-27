package com.chaity.android.easy.move.dagger.modules

import android.content.Context

import androidx.room.Room

import com.chaity.android.easy.move.MyApp
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
class ApplicationModule {

    @Singleton
    @Provides
    internal fun provideContext(application: MyApp): Context {
        return application
    }
}
