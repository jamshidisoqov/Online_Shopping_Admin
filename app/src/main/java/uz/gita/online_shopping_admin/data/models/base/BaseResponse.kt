package uz.gita.online_shopping_admin.data.models.base

// Created by Jamshid Isoqov an 10/11/2022
data class BaseResponse<T>(
    val successful: Boolean,
    val message: String? = "",
    val data: T,
    val status: Int = 200
)
