package uz.gita.online_shopping_admin.utils.extensions

import com.google.android.gms.maps.model.LatLng
import uz.gita.online_shopping_admin.data.models.data.AddressData

// Created by Jamshid Isoqov an 11/8/2022


fun LatLng.toAddress() = AddressData(this.latitude, this.longitude)