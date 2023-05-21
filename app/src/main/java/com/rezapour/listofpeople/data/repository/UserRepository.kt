package com.rezapour.listofpeople.data.repository

import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.models.User

interface UserRepository {

    suspend fun getCustomers(): List<Customers>

    suspend fun getUser(userId: Int): User
}