package com.rezapour.listofpeople.data.network.retrofit

import com.rezapour.listofpeople.data.models.user.UserNetworkEntity
import com.rezapour.listofpeople.data.models.users_list.UsersNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<UsersNetworkEntity>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserNetworkEntity>
}