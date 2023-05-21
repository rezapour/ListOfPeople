package com.rezapour.listofpeople.data.repository.impl

import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.repository.UserRepository
import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.models.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataProvider: NetworkDataProvider) :
    UserRepository {

    override suspend fun getCustomers(): List<Customers> = dataProvider.getCustomers()

    override suspend fun getUser(userId: Int): User = dataProvider.getUser(userId)
}