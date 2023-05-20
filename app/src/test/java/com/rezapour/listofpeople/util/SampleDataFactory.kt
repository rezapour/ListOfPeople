package com.rezapour.listofpeople.util

import com.rezapour.listofpeople.models.AddressDomain
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain

object SampleDataFactory {

    fun getCustomers(): List<CustomersDomain> {
        val customer1 = CustomersDomain(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            gender = "MALE",
            phoneNumber = "123-456-7890",
            imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
            stickers = arrayListOf("Fam")
        )

        val customer2 = CustomersDomain(
            id = 2,
            firstName = "Jane",
            lastName = "Doe",
            gender = "FEMALE",
            phoneNumber = "123-456-7891",
            imageUrl = "https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M",
            stickers = arrayListOf("Fam", "Ban")
        )
        return arrayListOf(customer1, customer2)
    }

    fun getUserDetail() = UserDomain(
        id = 1,
        imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
        currentLatitude = 37.7749,
        currentLongitude = -122.4194,
        firstName = "John",
        lastName = "Doe",
        stickers = arrayListOf("Fam"),
        gender = "MALE",
        phoneNumber = "123-456-7890",
        address = getAddress()
    )

    fun getAddress() = AddressDomain(
        street = "123 Main St",
        city = "San Francisco",
        state = "CA",
        zip = "94111",
        country = "USA"
    )

}