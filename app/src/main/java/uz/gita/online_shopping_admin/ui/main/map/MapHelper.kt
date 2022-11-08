package uz.gita.online_shopping_admin.ui.main.map

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

// Created by Jamshid Isoqov an 10/19/2022
class MapHelper : OnMapReadyCallback, SupportMapFragment() {

    private var map: (googleMap: GoogleMap) -> Unit = {}

    fun onMapReady(map: (googleMap: GoogleMap) -> Unit) {
        this.map = map
    }

    override fun onMapReady(p0: GoogleMap) {
        map.invoke(p0)
    }

}