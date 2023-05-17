package com.rezapour.listofpeople.data.models.users_list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersNetworkEntity(
    @SerializedName("customers") val customers: ArrayList<CustomersNetworkEntity>
)

data class CustomersNetworkEntity(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("firstName") val firstName: String,
    @Expose @SerializedName("lastName") val lastName: String,
    @Expose @SerializedName("gender") val gender: String,
    @Expose @SerializedName("phoneNumber") val phoneNumber: String,
    @Expose @SerializedName("imageUrl") val imageUrl: String,
    @Expose @SerializedName("stickers") val stickers: ArrayList<String>
)