package uz.gita.online_shopping_admin.ui.main.products

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.CategoryData
import uz.gita.online_shopping_admin.data.models.data.ProductData

// Created by Jamshid Isoqov an 11/7/2022
interface ProductsViewModel {

    val categoriesFlow: StateFlow<List<CategoryData>>

    val productsFlow: StateFlow<List<ProductData>>

    fun getProducts()

    fun categoryItemClick(categoryData: CategoryData,selectedPos:Int)

    fun openProductDetailsScreen(productData: ProductData)

    fun searchClicked()


}