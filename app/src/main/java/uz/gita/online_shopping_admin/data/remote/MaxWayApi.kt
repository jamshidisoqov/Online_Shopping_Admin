package uz.gita.online_shopping_admin.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.gita.online_shopping_admin.data.models.base.BaseResponse
import uz.gita.online_shopping_admin.data.models.data.*
import uz.gita.online_shopping_admin.data.models.dto.*
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus

// Created by Jamshid Isoqov an 11/7/2022
interface MaxWayApi {

    @POST("api/v1/admin/login")
    suspend fun loginAdmin(adminDto: AdminDto): Response<BaseResponse<AdminData>>

    @GET("api/v1/order/all")
    suspend fun getAllOrders(): Response<BaseResponse<List<OrderData>>>

    @PUT("api/v1/order/update/{id}")
    suspend fun updateOrderStatus(
        @Path("id") id: Long,
        @Field("status") orderStatus: OrderStatus
    ): Response<BaseResponse<OrderData>>

    @GET("api/v1/finance/count")
    suspend fun getOrderCountStatistics(
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<BaseResponse<List<Long>>>

    @GET("api/v1/finance/money")
    suspend fun getOrderMoneyStatistics(
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<BaseResponse<List<Double>>>

    @GET("api/v1/clients/all")
    suspend fun getAllClients(): Response<BaseResponse<List<ClientData>>>

    @GET("api/v1/clients/{id}")
    suspend fun getOrdersByClient(
        @Path("id") id: Long
    ): Response<BaseResponse<List<OrderData>>>

    @PUT("api/v1/clients/{id}")
    suspend fun updateClientStatus(
        @Path("id") id: Long,
        @Field("active") active: Boolean
    ): Response<BaseResponse<ClientData>>

    @GET("api/v1/category/all")
    suspend fun getAllCategories(): Response<BaseResponse<List<CategoryData>>>

    @GET("api/v1/product/all")
    suspend fun getAllProducts(): Response<BaseResponse<List<ProductData>>>

    @POST("api/v1/category/create")
    suspend fun addCategory(
        @Body categoryDto: CategoryDto
    ): Response<BaseResponse<CategoryData>>

    @POST("api/v1/product/create")
    suspend fun addProduct(
        @Body productDto: ProductDto
    ): Response<BaseResponse<ProductData>>

    @PUT("api/v1/category/update/{id}")
    suspend fun updateCategory(
        @Path("id") id: Long,
        @Body categoryDto: CategoryDto
    ): Response<BaseResponse<CategoryData>>

    @PUT("api/v1/product/update{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body productDto: ProductDto
    ): Response<BaseResponse<ProductData>>

    @DELETE("api/v1/category/delete/{id}")
    suspend fun deleteCategory(
        @Path("id") id: Long
    ): Response<BaseResponse<CategoryData>>

    @DELETE("api/v1/product/delete/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Long
    ): Response<BaseResponse<ProductData>>

    @GET("api/v1/branches/all")
    suspend fun getAllBranches(): Response<BaseResponse<List<BranchData>>>

    @POST("api/v1/branches/create")
    suspend fun addBranch(
        @Body branchDto: BranchDto
    ): Response<BaseResponse<BranchData>>

    @PUT("api/v1/branches/update/{id}")
    suspend fun updateBranch(
        @Path("id") id: Long,
        @Body branchDto: BranchDto
    ): Response<BaseResponse<BranchData>>

    @GET("api/v1/driver/all")
    suspend fun getAllDrivers(): Response<BaseResponse<List<DriverData>>>


    @POST("api/v1/driver/create")
    suspend fun addDriver(
        @Body driverDto: DriverDto
    ): Response<BaseResponse<DriverData>>

    @PUT("api/v1/driver/{id}")
    suspend fun updateDriver(
        @Path("id") id: Long,
        @Body driverDto: DriverDto
    )

    @DELETE("api/v1/driver/delete/{id}")
    suspend fun deleteDriver(
        @Path("id") id: Long
    ): Response<BaseResponse<DriverData>>

}