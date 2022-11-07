package uz.gita.online_shopping_admin.ui.main.products.search

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.ProductData

// Created by Jamshid Isoqov an 11/7/2022
interface SearchViewModel {

    val productFlow: StateFlow<List<ProductData>>

    fun getProducts()

    fun navigateToProductDetail(productData: ProductData)

    fun searchProducts(query: String)

}