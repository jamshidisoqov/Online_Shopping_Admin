package uz.gita.online_shopping_admin.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping_admin.data.models.base.ResultData
import uz.gita.online_shopping_admin.data.models.data.*
import uz.gita.online_shopping_admin.data.models.dto.BranchDto
import uz.gita.online_shopping_admin.data.models.dto.CategoryDto
import uz.gita.online_shopping_admin.data.models.dto.DriverDto
import uz.gita.online_shopping_admin.data.models.dto.ProductDto
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus

// Created by Jamshid Isoqov an 11/5/2022
interface Repository {

    suspend fun getToken(): String

    suspend fun setToken(token: String)

    suspend fun login(login: String, password: String): ResultData<String>

    fun getAllOrders(): Flow<ResultData<List<OrderData>>>

    suspend fun updateOrderStatus(status: OrderStatus): ResultData<OrderData>

    fun getOrderCountStatistics(start: String, end: String): Flow<ResultData<List<Long>>>

    fun getOrderMoneyStatistics(start: String, end: String): Flow<ResultData<List<Double>>>

    fun getAllClients(): Flow<ResultData<List<ClientData>>>

    fun getOrderByClient(clientData: ClientData): Flow<ResultData<List<OrderData>>>

    fun updateClientStatus(clientData: ClientData, active: Boolean): Flow<ResultData<ClientData>>

    fun getAllCategories(): Flow<ResultData<List<CategoryData>>>

    fun getAllProducts(): Flow<ResultData<List<ProductData>>>

    fun addCategory(categoryDto: CategoryDto): Flow<ResultData<CategoryData>>

    fun addProduct(productDto: ProductDto): Flow<ResultData<ProductData>>

    fun updateCategory(id: Long, categoryDto: CategoryDto): Flow<ResultData<CategoryData>>

    fun updateProduct(id: Long, productDto: ProductDto): Flow<ResultData<ProductData>>

    fun deleteCategory(id: Long): Flow<ResultData<CategoryData>>

    fun deleteProduct(id: Long): Flow<ResultData<ProductData>>

    fun getAllBranches(): Flow<ResultData<List<BranchData>>>

    fun addBranch(branchDto: BranchDto): Flow<ResultData<BranchData>>

    fun updateBranch(id: Long, branchDto: BranchDto): Flow<ResultData<BranchData>>

    fun getAllDrivers(): Flow<ResultData<List<DriverData>>>

    fun deleteDriver(driverDto: DriverDto): Flow<ResultData<DriverData>>

    fun addDriver(driverDto: DriverDto): Flow<ResultData<DriverData>>

    fun updateDriver(id: Long, driverDto: DriverDto): Flow<ResultData<DriverData>>

    fun getAllOrdersByDriver(driverData: DriverData): Flow<ResultData<List<OrderData>>>

    fun uploadImage(uri: Uri): Flow<ResultData<String>>


}