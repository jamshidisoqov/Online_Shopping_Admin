package uz.gita.online_shopping_admin.data.models.data

import uz.gita.online_shopping_admin.data.models.enums.OrderStatus
import uz.gita.online_shopping_admin.data.models.enums.OrderType
import java.io.Serializable

// Created by Jamshid Isoqov an 10/8/2022
data class OrderData(
    val id: Long,
    var createdDate: String,
    val products: List<ProductItem>,
    val allPrice: Double,
    val type: OrderType,
    val status: OrderStatus,
    val comment: String = "",
    val number: Long,
    val createdBy: ClientData
) : Serializable
