package com.valerytimofeev.googlemapsisslocation.data.remote

import com.google.android.gms.maps.model.LatLng
import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationResponse
import com.valerytimofeev.googlemapsisslocation.domain.model.IssLocation

fun IssLocationResponse.toIssLocation(): IssLocation {
    return IssLocation(
        latLng = LatLng(issPosition.latitude.toDouble(), issPosition.longitude.toDouble()),
        message = message,
        timestamp = timestamp
    )
}