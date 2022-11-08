package uz.gita.online_shopping_admin.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.local.prefs.MySharedPref
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.*
import uz.gita.online_shopping_admin.data.models.dto.BranchDto
import uz.gita.online_shopping_admin.data.models.dto.CategoryDto
import uz.gita.online_shopping_admin.data.models.dto.DriverDto
import uz.gita.online_shopping_admin.data.models.dto.ProductDto
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

    override fun getOrderCountStatistics(start: String, end: String): Flow<ResultData<List<Long>>> {
        TODO("Not yet implemented")
    }

    override fun getOrderMoneyStatistics(
        start: String,
        end: String
    ): Flow<ResultData<List<Double>>> {
        TODO("Not yet implemented")
    }

    override fun getAllClients(): Flow<ResultData<List<ClientData>>> {
        TODO("Not yet implemented")
    }

    override fun getOrderByClient(clientData: ClientData): Flow<ResultData<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override fun updateClientStatus(
        clientData: ClientData,
        active: Boolean
    ): Flow<ResultData<ClientData>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): Flow<ResultData<List<CategoryData>>> {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): Flow<ResultData<List<ProductData>>> {
        TODO("Not yet implemented")
    }

    override fun addCategory(categoryDto: CategoryDto): Flow<ResultData<CategoryData>> {
        TODO("Not yet implemented")
    }

    override fun addProduct(productDto: ProductDto): Flow<ResultData<ProductData>> {
        TODO("Not yet implemented")
    }

    override fun updateCategory(
        id: Long,
        categoryDto: CategoryDto
    ): Flow<ResultData<CategoryData>> {
        TODO("Not yet implemented")
    }

    override fun updateProduct(id: Long, productDto: ProductDto): Flow<ResultData<ProductData>> {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(id: Long): Flow<ResultData<CategoryData>> {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: Long): Flow<ResultData<ProductData>> {
        TODO("Not yet implemented")
    }

    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> {
        TODO("Not yet implemented")
    }

    override fun addBranch(branchDto: BranchDto): Flow<ResultData<BranchData>> {
        TODO("Not yet implemented")
    }

    override fun updateBranch(id: Long, branchDto: BranchDto): Flow<ResultData<BranchData>> {
        TODO("Not yet implemented")
    }

    override fun getAllDrivers(): Flow<ResultData<List<DriverData>>> {
        TODO("Not yet implemented")
    }

    override fun deleteDriver(driverDto: DriverDto): Flow<ResultData<DriverData>> {
        TODO("Not yet implemented")
    }

    override fun addDriver(driverDto: DriverDto): Flow<ResultData<DriverData>> {
        TODO("Not yet implemented")
    }

    override fun updateDriver(id: Long, driverDto: DriverDto): Flow<ResultData<DriverData>> {
        TODO("Not yet implemented")
    }
}