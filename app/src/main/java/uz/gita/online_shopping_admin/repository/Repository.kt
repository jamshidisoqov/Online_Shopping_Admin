package uz.gita.online_shopping_admin.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.*
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus

// Created by Jamshid Isoqov an 11/5/2022
interface Repository {

    suspend fun getToken(): String

    suspend fun setToken(token: String)

    suspend fun login(login: String, password: String): ResultData<String>

    fun getAllOrders(): Flow<ResultData<List<OrderData>>>

    suspend fun updateOrderStatus(status: OrderStatus): ResultData<OrderData>

    fun getAllClients(): Flow<ResultData<List<ClientData>>>

    fun getOrderByClient(clientData: ClientData): Flow<ResultData<List<OrderData>>>

    fun getAllDrivers(): Flow<ResultData<List<DriverData>>>

    suspend fun deleteDriver(driverData: DriverData): ResultData<DriverData>

    fun getAllCategories(): Flow<ResultData<List<CategoryData>>>

    fun getAllProducts(): Flow<ResultData<List<ProductData>>>

}