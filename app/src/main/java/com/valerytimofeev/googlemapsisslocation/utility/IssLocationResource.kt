package com.valerytimofeev.googlemapsisslocation.utility

sealed class IssLocationResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): IssLocationResource<T>(data)
    class Error<T>(data: T? = null, message: String): IssLocationResource<T>(data = data, message = message)
    class Loading<T>: IssLocationResource<T>()
}