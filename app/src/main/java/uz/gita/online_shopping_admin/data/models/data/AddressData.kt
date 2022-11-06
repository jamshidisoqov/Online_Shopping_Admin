package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov an 10/8/2022
@Parcelize
data class AddressData(
    val lat: Double,
    val lon: Double
) : Parcelable {
    fun toLatLong() = LatLng(lat, lon)
}
