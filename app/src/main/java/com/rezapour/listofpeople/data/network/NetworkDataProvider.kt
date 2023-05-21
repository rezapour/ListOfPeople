package com.rezapour.listofpeople.data.network

import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.models.User

interface NetworkDataProvider {

    suspend fun getCustomers(): List<Customers>

    suspend fun getUser(userId: Int): User
}