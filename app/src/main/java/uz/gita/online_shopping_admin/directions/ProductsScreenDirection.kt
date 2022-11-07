package uz.gita.online_shopping_admin.directions

import uz.gita.online_shopping_admin.data.models.data.ProductData

// Created by Jamshid Isoqov an 11/7/2022
interface ProductsScreenDirection {

    fun navigateToProductDetail(productData: ProductData)

    fun navigateToSearch()

    fun navigateToAddProducts()

}