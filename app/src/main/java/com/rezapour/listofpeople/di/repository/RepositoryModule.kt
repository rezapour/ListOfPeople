package com.rezapour.listofpeople.di.repository

import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.repository.UserRepository
import com.rezapour.listofpeople.data.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(dataProvider: NetworkDataProvider): UserRepository =
        UserRepositoryImpl(dataProvider)
}