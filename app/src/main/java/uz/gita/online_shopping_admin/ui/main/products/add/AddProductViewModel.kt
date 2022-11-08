package uz.gita.online_shopping_admin.ui.main.products.add

import android.net.Uri
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.CategoryData
import uz.gita.online_shopping_admin.data.models.dto.CategoryDto

// Created by Jamshid Isoqov an 11/7/2022
interface AddProductViewModel {

    val backSharedFlow: SharedFlow<Unit>

    val categoryFlow: StateFlow<List<CategoryData>>

    fun getAllCategory()

    fun addCategory(categoryDto: CategoryDto)

    fun addProduct(
        categoryData: CategoryData,
        name: String,
        price: Double,
        imgUri: Uri,
        desc: String
    )

}