package com.rezapour.listofpeople.data.network.models.users_list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersNetworkEntity(
    @Expose val customers: ArrayList<CustomersNetworkEntity>
)

data class CustomersNetworkEntity(
    @Expose val id: Int,
    @Expose val firstName: String,
    @Expose val lastName: String,
    @Expose val gender: String,
    @Expose val phoneNumber: String,
    @Expose val imageUrl: String,
    @Expose val stickers: ArrayList<String>
)