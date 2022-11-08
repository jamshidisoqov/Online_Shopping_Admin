package uz.gita.online_shopping_admin.ui.main.drivers.details

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.DriverData
import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/8/2022
interface DriverDetailViewModel {

    val orderByDrivers: StateFlow<List<OrderData>>

    fun getAllOrdersByDriver(driverData: DriverData)

    fun navigateToOrderDetail(orderData: OrderData)

}