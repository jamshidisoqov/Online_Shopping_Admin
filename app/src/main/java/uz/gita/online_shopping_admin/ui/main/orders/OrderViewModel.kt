package uz.gita.online_shopping_admin.ui.main.orders

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/6/2022
interface OrderViewModel {

    val orderByFilter: StateFlow<List<OrderData>>

    fun navigateToFilter()

    fun searchOrder(query: String)

    fun navigateToOrderDetails(orderData: OrderData)

    fun getOrders()

}