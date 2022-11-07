package uz.gita.online_shopping_admin.ui.main.clients.details

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/7/2022
interface ClientDetailViewModel {

    val orderByClient: StateFlow<List<OrderData>>

    fun getClientOrders(clientData: ClientData)

    fun navigateToOrderDetails(orderData: OrderData)
}