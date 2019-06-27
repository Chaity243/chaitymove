package com.chaity.android.easy.move.dagger.modules

import com.chaity.android.easy.move.api.DeliveryService
import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.db.DeliveryLocalCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    internal fun provideDeliveryRepository(service: DeliveryService, cache: DeliveryLocalCache): DeliveryRepository {
        return DeliveryRepository(service, cache)
    }
}