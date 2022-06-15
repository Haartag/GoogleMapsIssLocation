package com.valerytimofeev.googlemapsisslocation.domain.model

import com.google.android.gms.maps.model.LatLng

data class IssLocation(
    val latLng: LatLng,
    val message: String,
    val timestamp: Int
)
