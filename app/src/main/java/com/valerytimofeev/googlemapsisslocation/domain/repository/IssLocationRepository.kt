package com.valerytimofeev.googlemapsisslocation.domain.repository

interface IssLocationRepository {

    suspend fun getCurrentIssLocation()

}