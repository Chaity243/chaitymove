package com.chaity.android.easy.move.dagger.modules


import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.extension.*


import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun providesClient(): OkHttpClient? {
        okHttpClient = OkHttpClient.Builder()
                .build()

        return okHttpClient
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .client(providesClient())
                .baseUrl(BASE_URL)
                .client(providesClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): DeliveryService {
        return retrofit.create(DeliveryService::class.java)
    }

    companion object {

        private var okHttpClient: OkHttpClient? = null
    }
}
