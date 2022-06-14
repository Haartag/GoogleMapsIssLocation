package com.valerytimofeev.googlemapsisslocation.data.remote.responses


import com.google.gson.annotations.SerializedName

data class IssLocationResponse(
    @SerializedName("iss_position")
    val issPosition: IssPosition,
    val message: String,
    val timestamp: Int
)