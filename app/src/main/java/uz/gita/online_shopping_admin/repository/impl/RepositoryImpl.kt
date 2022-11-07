package uz.gita.online_shopping_admin.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.local.prefs.MySharedPref
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.*
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus
import uz.gita.online_shopping_admin.data.remote.MaxWayApi
import uz.gita.online_shopping_admin.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mySharedPref: MySharedPref,
    private val api: MaxWayApi
) : Repository {
    override suspend fun getToken(): String = mySharedPref.token

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

    override fun getAllClients(): Flow<ResultData<List<ClientData>>> {
        TODO("Not yet implemented")
    }

    override fun getOrderByClient(clientData: ClientData): Flow<ResultData<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override fun getAllDrivers(): Flow<ResultData<List<DriverData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDriver(driverData: DriverData): ResultData<DriverData> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): Flow<ResultData<List<CategoryData>>> {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): Flow<ResultData<List<ProductData>>> {
        TODO("Not yet implemented")
    }

    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> {
        TODO("Not yet implemented")
    }
}