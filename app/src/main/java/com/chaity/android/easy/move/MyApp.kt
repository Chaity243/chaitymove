package com.chaity.android.easy.move



import com.chaity.android.easy.move.dagger.components.DaggerDeliveryMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerDeliveryMainComponent.builder().create(this)
    }



}
