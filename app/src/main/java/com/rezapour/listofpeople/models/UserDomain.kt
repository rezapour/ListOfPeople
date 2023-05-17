package com.rezapour.listofpeople.models

data class UserDomain(
    val id: Int,
    val imageUrl: String,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val firstName: String,
    val lastName: String,
    val stickers: ArrayList<String>,
    val gender: String,
    val phoneNumber: String,
    val address: AddressDomain
)

data class AddressDomain(
    val street: String, val city: String, val state: String, val zip: String, val country: String
)