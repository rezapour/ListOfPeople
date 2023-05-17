package com.rezapour.listofpeople.data.network.impl

import com.rezapour.listofpeople.data.exception.DataProviderException
import com.rezapour.listofpeople.data.network.NetworkDataProvider
import com.rezapour.listofpeople.data.network.exception.ExceptionMapper
import com.rezapour.listofpeople.data.network.mapper.NetworkDataMapper
import com.rezapour.listofpeople.data.network.retrofit.ApiService
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.models.UserDomain
import retrofit2.Response
import javax.inject.Inject

class NetworkDataProviderImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: NetworkDataMapper
) :
    NetworkDataProvider {
    override suspend fun getCustomers(): List<CustomersDomain> {
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful)
                if (response.isResponseValid())
                    return mapper.usersNetworkEntityToListOfCustomerDomain(response.body()!!.customers)
                else
                    throw DataProviderException(ExceptionMapper.toServerError())
            else
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(response.code()))
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }

    override suspend fun getUser(userId: Int): UserDomain {
        try {
            val response = apiService.getUser(userId)
            if (response.isSuccessful)
                if (response.isResponseValid())
                    return mapper.userNetworkEntityToUserDomain(response.body()!!)
                else
                    throw DataProviderException(ExceptionMapper.toServerError())
            else
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(response.code()))
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }

    private fun <T> Response<T>.isResponseValid(): Boolean {
        return body() != null
    }
}