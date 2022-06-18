package com.valerytimofeev.googlemapsisslocation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.valerytimofeev.googlemapsisslocation.data.remote.toIssLocation


@Composable
fun MapSurface(
    viewModel: IssMapViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 2f)
    }

    Scaffold(scaffoldState = scaffoldState) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.mapProperties,
            cameraPositionState = cameraPositionState,
            //map loaded callback
            onMapLoaded = { viewModel.isMapLoaded.value = true }
        ) {
            if (viewModel.isMapLoaded.value) {
                //center map on marker
                if (viewModel.alignMap.value) {
                    cameraPositionState.position =
                        viewModel.getCameraPosition(viewModel.currentIssLocation.value.data!!.toIssLocation().latLng)
                    viewModel.alignMap.value = false
                }
                Marker(
                    position = viewModel.currentIssLocation.value.data!!.toIssLocation().latLng,
                    icon = BitmapDescriptorFactory.fromBitmap(viewModel.getMarkerBitmap(LocalContext.current)),
                    title = "${viewModel.currentIssLocation.value.data!!.toIssLocation().latLng}",
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                )
            }
        }
        //if API call return error, stop API track and show error message
        if (!viewModel.isLoading.value && viewModel.loadError.value.isNotEmpty()) {
            Error(error = viewModel.loadError.value) {
                viewModel.startTrackIssLocation()
            }
        }
        // lat/lng overlay
        if (viewModel.isMapLoaded.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(36.dp),
                contentAlignment = BottomStart
            ) {
                Text(
                    color = Color.White,
                    text = "Latitude: ${viewModel.currentIssLocation.value.data!!.toIssLocation().latLng.latitude} \n" +
                            "Longitude: ${viewModel.currentIssLocation.value.data!!.toIssLocation().latLng.longitude}"
                )
            }
        }
    }
}

@Composable
fun Error(
    error: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(alpha = 0.75f))
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Error:", color = Color.Red, fontSize = 20.sp)
            Text(text = error, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = { onRetry() }) {
                Text(text = "Retry")
            }
        }
    }
}