package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.ClientDetailDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.clients.details.ClientDetailViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class ClientDetailViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: ClientDetailDirection
) : ClientDetailViewModel, ViewModel() {

    override val orderByClient = MutableStateFlow<List<OrderData>>(emptyList())

    override fun getClientOrders(clientData: ClientData) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getOrderByClient(clientData).collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    orderByClient.emit(data)
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }
        }
    }

    override fun navigateToOrderDetails(orderData: OrderData) {
        viewModelScope.launch {
            direction.navigateToOrderDetails(orderData)
        }
    }
}