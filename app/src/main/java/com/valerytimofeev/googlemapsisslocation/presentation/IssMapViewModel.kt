package com.valerytimofeev.googlemapsisslocation.presentation

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.valerytimofeev.googlemapsisslocation.R
import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationResponse
import com.valerytimofeev.googlemapsisslocation.domain.repository.IssLocationRepository
import com.valerytimofeev.googlemapsisslocation.utility.Constants
import com.valerytimofeev.googlemapsisslocation.utility.IssLocationResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssMapViewModel @Inject constructor(
    private val repository: IssLocationRepository
) : ViewModel() {

    val mapProperties =
        MapProperties(mapStyleOptions = MapStyleOptions(MapDecoration.MapStyle.json))
    val loadingState = mutableStateOf(false)

    init {
        Log.d(Constants.TAG, "init")
    }

    //make custom icon from resource
    fun getMarkerBitmap(context: Context): Bitmap {
        val markerVector =
            ContextCompat.getDrawable(context, R.drawable.ic_iss)!!
        markerVector.setBounds(0, 0, markerVector.intrinsicWidth, markerVector.intrinsicHeight)
        val markerBitmap = Bitmap.createBitmap(
            markerVector.intrinsicWidth,
            markerVector.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = android.graphics.Canvas(markerBitmap)
        markerVector.draw(canvas)
        return markerBitmap
    }

    //make CameraPosition from Iss LatLng
    fun getCameraPosition(position: LatLng): CameraPosition {
        return CameraPosition.fromLatLngZoom(position, 2f)
    }

    //API test part
    suspend fun loadIss(): IssLocationResource<IssLocationResponse> {
        val a = repository.getCurrentIssLocation()
        Log.d(Constants.TAG, "MapSurface: ${a.data?.message} -> ${a.data?.issPosition?.latitude}")
        return a
    }
}