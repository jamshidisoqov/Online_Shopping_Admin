package uz.gita.online_shopping_admin.presenter

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping_admin.data.models.data.AddressData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.orders.details.OrderDetailViewModel
import uz.gita.online_shopping_admin.utils.exceptions.NoInternetConnection
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import uz.gita.online_shopping_admin.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModelImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val showed: Showed,
    private val repository: Repository
) : OrderDetailViewModel, ViewModel() {
    override val orderConfirmedFlow = MutableSharedFlow<Boolean>()

    override val address = MutableSharedFlow<String>()

    override fun getBranchLocation(address: AddressData) {
        val latLng = address.toLatLong()
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            try {
                if (hasConnection()) {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    this@OrderDetailViewModelImpl.address.emit(withContext(Dispatchers.IO) {
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

    override fun setOrderStatus(orderStatus: OrderStatus) {
        viewModelScope.launch {
            orderConfirmedFlow.emit(orderStatus != OrderStatus.DELIVERED)
        }
    }

    override fun orderConfirmed(orderData: OrderData) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.updateOrderStatus(OrderStatus.values()[orderData.status.ordinal + 1])
                .onError {
                    showed.setError(it.getMessage())
                }.onSuccess {
                    orderConfirmedFlow.emit(it.status != OrderStatus.DELIVERED)
                }.onMessage {
                    showed.setMessage(it)
                }
            showed.setLoading(false)
        }
    }
}