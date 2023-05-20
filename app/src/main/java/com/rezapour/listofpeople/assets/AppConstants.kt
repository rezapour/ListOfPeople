package com.rezapour.listofpeople.assets

object AppConstants {
    const val BASE_URL = "https://api.code-challenge.patronus-group.com/"
    const val TIME_OUT: Long = 5000

    const val STATIC_MAP_URL =
        "https://maps.geoapify.com/v1/staticmap?style=osm-carto&width=600&height=300&center=lonlat:{0},{1}&zoom=16&marker=lonlat:{0},{1};color:%23ff0000;size:small|lonlat:{0},{1};type:material;color:%23ff3421;icontype:awesome&apiKey=883f85020cbb4b1ea95f18a267f19f15"
}