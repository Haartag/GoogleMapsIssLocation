package com.valerytimofeev.googlemapsisslocation.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap


@Composable
fun MapSurface(
    viewModel: IssMapViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.mapProperties
        ) {

        }
    }
}