package uz.gita.online_shopping_admin.directions

import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/8/2022
interface DriverDetailDirection {

    suspend fun navigateToOrderDetails(orderData: OrderData)

}