package uz.gita.online_shopping_admin.presenter

import android.annotation.SuppressLint
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.map.MapViewModel
import uz.gita.online_shopping_admin.utils.exceptions.NoInternetConnection
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import uz.gita.online_shopping_admin.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class MapViewModelImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val showed: Showed
) : MapViewModel, ViewModel() {

    override val address = MutableSharedFlow<String>()

    override val currentLocationFlow = MutableLiveData<LatLng>()

    private var cancellationTokenSource = CancellationTokenSource()

    override fun getAddressByLocation(latLng: LatLng) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            try {
                if (hasConnection()) {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    address.emit(withContext(Dispatchers.IO) {
                        showed.setLoading(false)
                        try {
                            if (addresses.size > 0) addresses[0].getAddressLine(0)
                            else "Не указан"
                        } catch (e: Exception) {
                            e.localizedMessage
                        }
                    }!!)
                } else throw NoInternetConnection()
            } catch (e: Exception) {
                showed.setLoading(false)
                showed.setError(e.getMessage())
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun requestCurrentLocation() {
        viewModelScope.launch {
            showed.setLoading(true)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val location = task.result
                        currentLocationFlow.postValue(LatLng(location.latitude, location.longitude))
                    }
                }
        }

    }
}