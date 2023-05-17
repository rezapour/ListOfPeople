package com.rezapour.listofpeople.data.network.mapper

import com.rezapour.listofpeople.data.network.models.user.AddressNetworkEntity
import com.rezapour.listofpeople.data.network.models.user.UserNetworkEntity
import com.rezapour.listofpeople.data.network.models.users_list.CustomersNetworkEntity
import com.rezapour.listofpeople.data.network.models.users_list.UsersNetworkEntity
import com.rezapour.listofpeople.models.AddressDomain
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() {

    private fun customersNetworkEntityToCustomerDomain(customer: CustomersNetworkEntity): CustomersDomain =
        CustomersDomain(
            id = customer.id,
            firstName = customer.firstName,
            lastName = customer.lastName,
            gender = customer.gender,
            phoneNumber = customer.phoneNumber,
            imageUrl = customer.imageUrl,
            stickers = customer.stickers
        )

    fun usersNetworkEntityToListOfCustomerDomain(customers: List<CustomersNetworkEntity>): List<CustomersDomain> =
        customers.map { customer -> customersNetworkEntityToCustomerDomain(customer) }

    fun userNetworkEntityToUserDomain(user: UserNetworkEntity): UserDomain =
        UserDomain(
            id = user.id,
            imageUrl = user.imageUrl,
            currentLatitude = user.currentLatitude,
            currentLongitude = user.currentLongitude,
            firstName = user.firstName,
            lastName = user.lastName,
            stickers = user.stickers,
            gender = user.gender,
            phoneNumber = user.phoneNumber,
            address = addressNetworkEntityToAddressDomain(user.address)
        )

    private fun addressNetworkEntityToAddressDomain(address: AddressNetworkEntity): AddressDomain =
        AddressDomain(
            street = address.street,
            city = address.city,
            state = address.state,
            zip = address.zip,
            country = address.country
        )
}