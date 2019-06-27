package com.chaity.android.easy.move


import com.chaity.android.easy.move.dagger.components.DaggerHorizonMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class HorizonMainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        /* //Fabric for Crashlytics
        Fabric.with(this, new Crashlytics());

        //HyperLog for Remote Logging
        //HyperLog.initialize(this);
        //HyperLog.setLogLevel(Log.VERBOSE);

        instance = this;

        //Fresco for image library
        Fresco.initialize(this);*/
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerHorizonMainComponent.builder().create(this)
    }

    companion object {

        @get:Synchronized
        val instance: HorizonMainApplication? = null
        private val TAG = HorizonMainApplication::class.java.simpleName
    }

}
