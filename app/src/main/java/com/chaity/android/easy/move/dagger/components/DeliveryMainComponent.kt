package com.chaity.android.easy.move.dagger.components


import com.chaity.android.easy.move.MyApp
import com.chaity.android.easy.move.dagger.builders.ActivityBuilder
import com.chaity.android.easy.move.dagger.modules.DatabaseModule
import com.chaity.android.easy.move.dagger.modules.ApplicationModule
import com.chaity.android.easy.move.dagger.modules.ExecutorModule
import com.chaity.android.easy.move.dagger.modules.NetworkModule

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, NetworkModule::class, ActivityBuilder::class, ApplicationModule::class, DatabaseModule::class,ExecutorModule::class])
interface DeliveryMainComponent : AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>()

}