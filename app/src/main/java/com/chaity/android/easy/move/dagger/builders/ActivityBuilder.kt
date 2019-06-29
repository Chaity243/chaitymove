package com.chaity.android.easy.move.dagger.builders


import com.chaity.android.easy.move.ui.delivery.DeliveryActivity
import com.chaity.android.easy.move.dagger.modules.activityModules.DeliveryActivityModule

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [DeliveryActivityModule::class])
    internal abstract fun contributeDeliveriesActivity(): DeliveryActivity


}
