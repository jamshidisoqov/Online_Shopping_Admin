package uz.gita.online_shopping_admin.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Created by Jamshid Isoqov an 11/6/2022
@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @[Provides Singleton]
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder = Geocoder(context)

    @[Provides Singleton]
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

}