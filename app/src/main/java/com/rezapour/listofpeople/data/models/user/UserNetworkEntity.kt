package com.rezapour.listofpeople.data.models.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserNetworkEntity(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("imageUrl") val imageUrl: String,
    @Expose @SerializedName("currentLatitude") val currentLatitude: Double,
    @Expose @SerializedName("currentLongitude") val currentLongitude: Double,
    @Expose @SerializedName("firstName") val firstName: String,
    @Expose @SerializedName("lastName") val lastName: String,
    @Expose @SerializedName("stickers") val stickers: ArrayList<String>,
    @Expose @SerializedName("gender") val gender: String,
    @Expose @SerializedName("phoneNumber") val phoneNumber: String,
    @Expose @SerializedName("address") val address: AddressNetworkEntity
)

data class AddressNetworkEntity(
    @Expose @SerializedName("street") val street: String,
    @Expose @SerializedName("city") val city: String,
    @Expose @SerializedName("state") val state: String,
    @Expose @SerializedName("zip") val zip: String,
    @Expose @SerializedName("country") val country: String
)