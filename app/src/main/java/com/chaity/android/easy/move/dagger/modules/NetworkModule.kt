package com.chaity.android.easy.move.dagger.modules


import com.chaity.android.easy.move.BuildConfig
import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.extension.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun providesClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()

    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient : OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): DeliveryService {
        return retrofit.create(DeliveryService::class.java)
    }

}
