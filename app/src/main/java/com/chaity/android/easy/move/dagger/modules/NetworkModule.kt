package com.chaity.android.easy.move.dagger.modules


import com.chaity.android.easy.move.BuildConfig
import com.chaity.android.easy.move.api.DeliveryAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {


    @Singleton
    @Provides
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }



    @Singleton
    @Provides
    internal fun providesClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

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
    internal fun provideApiService(retrofit: Retrofit): DeliveryAPI {
        return retrofit.create(DeliveryAPI::class.java)
    }

}
