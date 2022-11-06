package uz.gita.online_shopping_admin.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.OrderData

// Created by Jamshid Isoqov an 11/5/2022
interface Repository {

    suspend fun getToken(): String

    suspend fun setToken(token: String)

    suspend fun login(login: String, password: String): ResultData<String>

    fun getAllOrders(): Flow<ResultData<List<OrderData>>>

}