package com.rezapour.listofpeople.di.network

import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.network.impl.NetworkDataProviderImpl
import com.rezapour.listofpeople.data.network.mapper.NetworkDataMapper
import com.rezapour.listofpeople.data.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDataProviderModule {

    @Singleton
    @Provides
    fun provideNetworkDataProvider(
        apiService: ApiService,
        mapper: NetworkDataMapper
    ): NetworkDataProvider = NetworkDataProviderImpl(apiService, mapper)
}