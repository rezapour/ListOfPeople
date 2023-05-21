package com.rezapour.listofpeople.data.network.models.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserNetworkEntity(
    @Expose val id: Int,
    @Expose val imageUrl: String,
    @Expose val currentLatitude: Double,
    @Expose val currentLongitude: Double,
    @Expose val firstName: String,
    @Expose val lastName: String,
    @Expose val stickers: ArrayList<String>,
    @Expose val gender: String,
    @Expose val phoneNumber: String,
    @Expose val address: AddressNetworkEntity
)

data class AddressNetworkEntity(
    @Expose val street: String,
    @Expose val city: String,
    @Expose val state: String,
    @Expose val zip: String,
    @Expose val country: String
)