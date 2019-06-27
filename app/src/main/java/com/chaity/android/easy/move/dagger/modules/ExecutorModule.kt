package com.chaity.android.easy.move.dagger.modules

import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ExecutorModule {


    @Singleton
    @Provides
    internal fun providesExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }


}