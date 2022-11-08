package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.DriverData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.DriverDetailDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.drivers.details.DriverDetailViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class DriverDetailViewModelImpl @Inject constructor(
    private val showed: Showed,
    private val repository: Repository,
    private val direction: DriverDetailDirection
) : DriverDetailViewModel, ViewModel() {

    override val orderByDrivers = MutableStateFlow<List<OrderData>>(emptyList())

    override fun getAllOrdersByDriver(driverData: DriverData) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getAllOrdersByDriver(driverData).collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    orderByDrivers.emit(data)
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }
        }
    }

    override fun navigateToOrderDetail(orderData: OrderData) {
        viewModelScope.launch {
            direction.navigateToOrderDetails(orderData)
        }
    }
}