package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.OrderScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.orders.OrderViewModel
import uz.gita.online_shopping_admin.utils.Filter
import uz.gita.online_shopping_admin.utils.enums.FilterStatus
import uz.gita.online_shopping_admin.utils.enums.FilterType
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class OrderViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: OrderScreenDirection
) : OrderViewModel, ViewModel() {

    private var orders: List<OrderData> = emptyList()

    override val orderByFilter = MutableStateFlow<List<OrderData>>(emptyList())

    private var date: Pair<Long, Long>? = null

    private var type: FilterType = FilterType.ALL

    private var status: FilterStatus = FilterStatus.ORDERED

    init {
        viewModelScope.launch {
            Filter.dateStateFlow.collectLatest {
                date = it
                getOrders()
            }
            Filter.typeStateFlow.collectLatest {
                type = it
                getOrders()
            }
            Filter.statusStateFlow.collectLatest {
                status = it
                getOrders()
            }
        }
    }

    override fun navigateToFilter() {
        viewModelScope.launch {
            direction.navigateToFilter()
        }
    }

    override fun searchOrder(query: String) {
        viewModelScope.launch(Dispatchers.Default){
            val list = orders.filter {orderData ->
                orderData.createdBy.fullName.startsWith(query)
            }
            orderByFilter.emit(list)
        }
    }

    override fun navigateToOrderDetails(orderData: OrderData) {
        viewModelScope.launch {
            direction.navigateToOrderDetails(orderData)
        }
    }

    override fun getOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllOrders().collectLatest {
                it.onSuccess { list ->
                    val filterList = withContext(Dispatchers.Default) {
                        list.filter { order ->
                            if (type == FilterType.ALL) true
                            else order.type.name == type.name
                        }.filter { orderData ->
                            if (status == FilterStatus.ALL) true
                            else status.name == orderData.status.name
                        }
                    }
                    orders = filterList
                    orderByFilter.emit(filterList)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }.onMessage { message ->
                    showed.setMessage(message)
                }
            }
        }
    }
}