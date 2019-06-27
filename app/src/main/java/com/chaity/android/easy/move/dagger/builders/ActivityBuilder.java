package com.chaity.android.easy.move.dagger.builders;



import com.chaity.android.easy.move.ui.delivery.DeliveriesActivity;
import com.chaity.android.easy.move.ui.delivery.DeliveryActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {



    @ContributesAndroidInjector(modules = DeliveryActivityModule.class)
    abstract DeliveriesActivity contributeDeliveriesActivity();




}
