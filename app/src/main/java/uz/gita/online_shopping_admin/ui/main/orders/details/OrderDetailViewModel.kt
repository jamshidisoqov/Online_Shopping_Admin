package uz.gita.online_shopping_admin.ui.main.orders.details

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping_admin.data.models.data.AddressData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus

// Created by Jamshid Isoqov an 11/6/2022
interface OrderDetailViewModel {

    val orderConfirmedFlow: SharedFlow<Boolean>

    val address: SharedFlow<String>

    fun getBranchLocation(address: AddressData)

    fun setOrderStatus(orderStatus: OrderStatus)

    fun orderConfirmed(orderData: OrderData)
}