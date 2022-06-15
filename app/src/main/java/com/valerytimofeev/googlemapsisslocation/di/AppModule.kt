package com.valerytimofeev.googlemapsisslocation.di

import com.valerytimofeev.googlemapsisslocation.data.remote.IssLocationApi
import com.valerytimofeev.googlemapsisslocation.data.remote.responses.IssLocationRepositoryImpl
import com.valerytimofeev.googlemapsisslocation.domain.repository.IssLocationRepository
import com.valerytimofeev.googlemapsisslocation.utility.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIssLocationRepository(api: IssLocationApi): IssLocationRepository {
        return IssLocationRepositoryImpl(api = api)
    }

    @Singleton
    @Provides
    fun provideIssLocationApi(): IssLocationApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(IssLocationApi::class.java)
    }
}