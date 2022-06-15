package com.valerytimofeev.googlemapsisslocation.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationResponse
import com.valerytimofeev.googlemapsisslocation.data.remote.toIssLocation
import com.valerytimofeev.googlemapsisslocation.utility.Constants.TAG
import com.valerytimofeev.googlemapsisslocation.utility.IssLocationResource


@Composable
fun MapSurface(
    viewModel: IssMapViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val locationState =
        produceState<IssLocationResource<IssLocationResponse>>(
            initialValue = IssLocationResource.Loading()
        ) {
            value = viewModel.loadIss()
        }

    Scaffold(scaffoldState = scaffoldState) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.mapProperties
        ) {
            if (locationState.value is IssLocationResource.Success) {
                Marker(
                    position = locationState.value.data!!.toIssLocation().latLng,
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                    title = "Spot (${locationState.value.data!!.toIssLocation().latLng})",
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                )
            }
        }
    }
}