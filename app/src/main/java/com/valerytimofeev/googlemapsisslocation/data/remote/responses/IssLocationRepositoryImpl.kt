package com.valerytimofeev.googlemapsisslocation.data.remote.responses

import com.valerytimofeev.googlemapsisslocation.data.remote.IssLocationApi
import com.valerytimofeev.googlemapsisslocation.domain.repository.IssLocationRepository
import com.valerytimofeev.googlemapsisslocation.utility.IssLocationResource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject


@ActivityScoped
class IssLocationRepositoryImpl @Inject constructor(
    private val api: IssLocationApi
): IssLocationRepository {
    override suspend fun getCurrentIssLocation(): IssLocationResource<IssLocationResponse> {
        val response = try {
            api.getIssLocation()
        } catch (e: Exception) {
            return IssLocationResource.Error(message = "try to check your internet connection \n\n $e")
        }
        return IssLocationResource.Success(response)
    }
}