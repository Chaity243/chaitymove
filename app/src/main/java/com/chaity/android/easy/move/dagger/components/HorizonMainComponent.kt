package com.chaity.android.easy.move.dagger.components


import com.chaity.android.easy.move.HorizonMainApplication
import com.chaity.android.easy.move.dagger.builders.ActivityBuilder
import com.chaity.android.easy.move.dagger.modules.HorizonAppModule
import com.chaity.android.easy.move.dagger.modules.NetworkModule

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, HorizonAppModule::class,

    NetworkModule::class, ActivityBuilder::class, HorizonAppModule::class])
interface HorizonMainComponent : AndroidInjector<HorizonMainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<HorizonMainApplication>()

}