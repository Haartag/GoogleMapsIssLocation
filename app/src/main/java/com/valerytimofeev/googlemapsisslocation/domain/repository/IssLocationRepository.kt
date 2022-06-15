package com.valerytimofeev.googlemapsisslocation.domain.repository

import com.valerytimofeev.googlemapsisslocation.data.remote.IssLocationApi
import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationResponse
import com.valerytimofeev.googlemapsisslocation.utility.IssLocationResource

interface IssLocationRepository {

    suspend fun getCurrentIssLocation(): IssLocationResource<IssLocationResponse>

}