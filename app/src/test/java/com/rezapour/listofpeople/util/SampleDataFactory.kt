package com.rezapour.listofpeople.util

import com.rezapour.listofpeople.data.network.models.user.AddressNetworkEntity
import com.rezapour.listofpeople.data.network.models.user.UserNetworkEntity
import com.rezapour.listofpeople.data.network.models.users_list.CustomersNetworkEntity
import com.rezapour.listofpeople.models.Address
import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.models.User

object SampleDataFactory {

    fun getCustomers(): List<Customers> {
        val customer1 = Customers(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            gender = "MALE",
            phoneNumber = "123-456-7890",
            imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
            stickers = arrayListOf("Fam")
        )

        val customer2 = Customers(
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

    fun getUserDetail() = User(
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

    private fun getAddress() = Address(
        street = "123 Main St",
        city = "San Francisco",
        state = "CA",
        zip = "94111",
        country = "USA"
    )

    fun getCustomersNetworkEntity(): List<CustomersNetworkEntity> {
        val customer1 = CustomersNetworkEntity(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            gender = "MALE",
            phoneNumber = "123-456-7890",
            imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
            stickers = arrayListOf("Fam")
        )

        val customer2 = CustomersNetworkEntity(
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

    fun getUserNetworkEntity() = UserNetworkEntity(
        id = 1,
        imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
        currentLatitude = 37.7749,
        currentLongitude = -122.4194,
        firstName = "John",
        lastName = "Doe",
        stickers = arrayListOf("Fam"),
        gender = "MALE",
        phoneNumber = "123-456-7890",
        address = getAddressNetWorkEntity()
    )

    private fun getAddressNetWorkEntity() = AddressNetworkEntity(
        street = "123 Main St",
        city = "San Francisco",
        state = "CA",
        zip = "94111",
        country = "USA"
    )

}