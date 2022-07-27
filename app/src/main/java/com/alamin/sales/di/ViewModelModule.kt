package com.alamin.sales.di

import androidx.lifecycle.ViewModel
import com.alamin.sales.view_model.StoreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(StoreViewModel::class)
    @IntoMap
    abstract fun provideStoreViewModel(storeViewModel: StoreViewModel): ViewModel
}