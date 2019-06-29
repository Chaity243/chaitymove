package com.chaity.android.easy.move.dagger.modules

import android.content.Context

import com.chaity.android.easy.move.MyApp


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
