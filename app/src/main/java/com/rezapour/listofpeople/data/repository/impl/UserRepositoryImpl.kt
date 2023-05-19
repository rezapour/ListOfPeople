package com.rezapour.listofpeople.data.repository.impl

import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.repository.UserRepository
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataProvider: NetworkDataProvider) :
    UserRepository {

    override suspend fun getCustomers(): List<CustomersDomain> = dataProvider.getCustomers()

    override suspend fun getUser(userId: Int): UserDomain = dataProvider.getUser(userId)
}