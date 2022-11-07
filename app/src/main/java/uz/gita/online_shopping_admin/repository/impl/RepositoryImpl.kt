package uz.gita.online_shopping_admin.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.local.prefs.MySharedPref
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus
import uz.gita.online_shopping_admin.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mySharedPref: MySharedPref
) : Repository {
    override suspend fun getToken(): String  = mySharedPref.token

    override suspend fun setToken(token: String) {
        mySharedPref.token = token
    }

    override suspend fun login(login: String, password: String): ResultData<String> {
        TODO("Not yet implemented")
    }

    override fun getAllOrders(): Flow<ResultData<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrderStatus(status: OrderStatus): ResultData<OrderData> {
        TODO("Not yet implemented")
    }
}