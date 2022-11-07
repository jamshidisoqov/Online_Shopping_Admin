package uz.gita.online_shopping_admin.data.models.dto

import uz.gita.online_shopping_admin.data.models.data.CategoryData

// Created by Jamshid Isoqov an 11/7/2022
data class ProductDto(
    val name: String,
    val category: CategoryData? = null,
    val imageUrl: String,
    val price: Double,
    val description: String
)
