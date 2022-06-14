package com.valerytimofeev.googlemapsisslocation.data.remote

import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationResponse
import retrofit2.http.GET

interface IssLocationApi {

    @GET("iss-now")
    suspend fun getIssLocation(): IssLocationResponse
}