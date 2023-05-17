package com.rezapour.listofpeople.data.repository

import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain

interface UserRepository {

    suspend fun getCustomers(): List<CustomersDomain>

    suspend fun getUser(userId: Int): UserDomain
}