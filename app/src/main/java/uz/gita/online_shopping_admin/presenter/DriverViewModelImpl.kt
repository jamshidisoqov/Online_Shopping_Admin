package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.DriverData
import uz.gita.online_shopping_admin.directions.DriverScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.drivers.DriverViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class DriverViewModelImpl @Inject constructor(
    private val showed: Showed,
    private val repository: Repository,
    private val direction: DriverScreenDirection
) : DriverViewModel, ViewModel() {

    override val driversFlow = MutableStateFlow<List<DriverData>>(emptyList())

    override val openDriverDelete = MutableSharedFlow<DriverData>()

    private var drivers: List<DriverData> = emptyList()

    override fun getDriver() {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getAllDrivers().collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    driversFlow.emit(data)
                    drivers = data
                }.onError { error ->
                    showed.setError(error.getMessage())
                }.onMessage { message ->
                    showed.setMessage(message)
                }
            }
        }
    }

    override fun navigateToDriverDetail(driverData: DriverData) {
        viewModelScope.launch {
            direction.navigateToDriverDetail(driverData)
        }
    }

    override fun driverDeleteClick(driverData: DriverData) {
        viewModelScope.launch {
            openDriverDelete.emit(driverData)
        }
    }

    override fun deleteDriver(driverData: DriverData) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.deleteDriver(driverData).onSuccess {
                showed.setToast("Successfully deleted")
            }.onMessage { message ->
                showed.setMessage(message)
            }.onError { error ->
                showed.setError(error.getMessage())
            }
            showed.setLoading(false)
        }
    }

    override fun searchDriver(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            drivers = drivers.filter {
                it.name.startsWith(query)
            }
            driversFlow.emit(drivers)
        }
    }
}