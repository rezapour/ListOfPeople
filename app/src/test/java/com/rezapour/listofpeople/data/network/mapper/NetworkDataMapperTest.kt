package com.rezapour.listofpeople.data.network.mapper


import com.google.common.truth.Truth.assertThat
import com.rezapour.listofpeople.util.SampleDataFactory
import org.junit.Before
import org.junit.Test

class NetworkDataMapperTest {
    lateinit var mapper: NetworkDataMapper

    @Before
    fun setUp() {
        mapper = NetworkDataMapper()
    }

    @Test
    fun customersNetworkEntityToCustomerDomainTest() {
        assertThat(mapper.usersNetworkEntityToListOfCustomerDomain(SampleDataFactory.getCustomersNetworkEntity())).isEqualTo(
            SampleDataFactory.getCustomers()
        )
    }

    @Test
    fun userNetworkEntityToUserDomainTest() {
        assertThat(mapper.userNetworkEntityToUserDomain(SampleDataFactory.getUserNetworkEntity())).isEqualTo(
            SampleDataFactory.getUserDetail()
        )
    }

}