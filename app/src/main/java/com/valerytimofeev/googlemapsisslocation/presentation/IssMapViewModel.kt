package com.valerytimofeev.googlemapsisslocation.presentation

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

class IssMapViewModel : ViewModel() {

    val mapProperties = MapProperties(mapStyleOptions = MapStyleOptions(MapDecoration.MapStyle.json))

}