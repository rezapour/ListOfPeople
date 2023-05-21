package com.rezapour.listofpeople.data.network.mapper

import com.rezapour.listofpeople.data.network.models.user.AddressNetworkEntity
import com.rezapour.listofpeople.data.network.models.user.UserNetworkEntity
import com.rezapour.listofpeople.data.network.models.users_list.CustomersNetworkEntity
import com.rezapour.listofpeople.models.Address
import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.models.User
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() {

    private fun customersNetworkEntityToCustomerDomain(customer: CustomersNetworkEntity): Customers =
        with(customer) {
            Customers(
                id = id,
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                phoneNumber = phoneNumber,
                imageUrl = imageUrl,
                stickers = stickers
            )
        }

    fun usersNetworkEntityToListOfCustomerDomain(customers: List<CustomersNetworkEntity>): List<Customers> =
        customers.map { customer -> customersNetworkEntityToCustomerDomain(customer) }

    fun userNetworkEntityToUserDomain(user: UserNetworkEntity): User =
        with(user) {
            User(
                id = id,
                imageUrl = imageUrl,
                currentLatitude = currentLatitude,
                currentLongitude = currentLongitude,
                firstName = firstName,
                lastName = lastName,
                stickers = stickers,
                gender = gender,
                phoneNumber = phoneNumber,
                address = addressNetworkEntityToAddressDomain(user.address)
            )
        }

    private fun addressNetworkEntityToAddressDomain(address: AddressNetworkEntity): Address =
        with(address) {
            Address(
                street = street,
                city = city,
                state = state,
                zip = zip,
                country = country
            )
        }
}