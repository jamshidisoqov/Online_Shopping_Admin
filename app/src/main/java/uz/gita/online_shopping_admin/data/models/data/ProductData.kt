package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.online_shopping_admin.data.models.base.BaseData
@Parcelize
data class ProductData(
    val name: String,
    val category: CategoryData? = null,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val createdBy: ClientData
) : BaseData(),Parcelable