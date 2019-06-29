package com.chaity.android.easy.move.dagger.modules.activityModules

import androidx.lifecycle.ViewModelProvider

import com.chaity.android.easy.move.data.DeliveryRepository
import com.chaity.android.easy.move.ui.delivery.DeliveriesViewModel
import com.chaity.android.easy.move.ui.delivery.DeliveryAdapter
import com.chaity.android.easy.move.utils.ViewModelProviderFactory
import dagger.Module

import dagger.Provides

@Module
class DeliveryActivityModule {

    @Provides
     fun provideDeliveryViewModel(repository: DeliveryRepository): DeliveriesViewModel {
        return DeliveriesViewModel(repository)
    }

   /* @Provides
     fun provideDeliveryAdapter(): DeliveryAdapter {
        return DeliveryAdapter()
    }*/

    @Provides
     fun provideViewModelProvider(viewModel: DeliveriesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<DeliveriesViewModel>(viewModel)
    }


}
