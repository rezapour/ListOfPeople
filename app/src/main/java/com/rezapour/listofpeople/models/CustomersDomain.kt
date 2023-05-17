package com.rezapour.listofpeople.models

data class CustomersDomain(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String,
    val stickers: ArrayList<String>
)

