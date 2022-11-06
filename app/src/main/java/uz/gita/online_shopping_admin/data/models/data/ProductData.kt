package uz.gita.online_shopping_admin.data.models.data

import uz.gita.online_shopping_admin.data.models.base.BaseData

data class ProductData(
    val name: String,
    val category: CategoryData? = null,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val createdBy: ClientData
) : BaseData()