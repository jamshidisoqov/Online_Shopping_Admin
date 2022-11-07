package uz.gita.online_shopping_admin.directions

import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/7/2022
interface ClientDetailDirection {

    suspend fun navigateToOrderDetails(orderData: OrderData)

}