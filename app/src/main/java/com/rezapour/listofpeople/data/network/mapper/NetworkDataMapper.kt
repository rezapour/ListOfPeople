package com.rezapour.listofpeople.data.network.mapper

import com.rezapour.listofpeople.data.network.models.user.AddressNetworkEntity
import com.rezapour.listofpeople.data.network.models.user.UserNetworkEntity
import com.rezapour.listofpeople.data.network.models.users_list.CustomersNetworkEntity
import com.rezapour.listofpeople.models.AddressDomain
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() {

    private fun customersNetworkEntityToCustomerDomain(customer: CustomersNetworkEntity): CustomersDomain =
        with(customer) {
            CustomersDomain(
                id = id,
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                phoneNumber = phoneNumber,
                imageUrl = imageUrl,
                stickers = stickers
            )
        }

    fun usersNetworkEntityToListOfCustomerDomain(customers: List<CustomersNetworkEntity>): List<CustomersDomain> =
        customers.map { customer -> customersNetworkEntityToCustomerDomain(customer) }

    fun userNetworkEntityToUserDomain(user: UserNetworkEntity): UserDomain =
        with(user) {
            UserDomain(
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

    private fun addressNetworkEntityToAddressDomain(address: AddressNetworkEntity): AddressDomain =
        with(address) {
            AddressDomain(
                street = street,
                city = city,
                state = state,
                zip = zip,
                country = country
            )
        }
}