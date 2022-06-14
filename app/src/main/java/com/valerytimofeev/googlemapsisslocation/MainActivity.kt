package com.valerytimofeev.googlemapsisslocation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.valerytimofeev.googlemapsisslocation.presentation.MapSurface
import com.valerytimofeev.googlemapsisslocation.ui.theme.GoogleMapsIssLocationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapsIssLocationTheme {
                MapSurface()
            }
        }
    }
}
