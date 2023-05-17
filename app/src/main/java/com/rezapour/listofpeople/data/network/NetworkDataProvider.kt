package com.rezapour.listofpeople.data.network

import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain

interface NetworkDataProvider {

    suspend fun getCustomers(): List<CustomersDomain>

    suspend fun getUser(userId: Int): UserDomain
}