package com.valerytimofeev.googlemapsisslocation.presentation

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.valerytimofeev.googlemapsisslocation.R
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
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 2f)
    }

    Scaffold(scaffoldState = scaffoldState) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.mapProperties,
            cameraPositionState = cameraPositionState
        ) {
            if (locationState.value is IssLocationResource.Success) {
                //center map on marker
                cameraPositionState.position = viewModel.getCameraPosition(locationState.value.data!!.toIssLocation().latLng)
                Marker(
                    position = locationState.value.data!!.toIssLocation().latLng,
                    icon = BitmapDescriptorFactory.fromBitmap(viewModel.getMarkerBitmap(LocalContext.current)),
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