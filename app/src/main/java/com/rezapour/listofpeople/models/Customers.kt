package com.rezapour.listofpeople.models

data class Customers(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String?,
    val stickers: ArrayList<String>
) {
    val fullName get() = "$firstName $lastName"
}

