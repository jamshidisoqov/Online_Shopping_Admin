package uz.gita.online_shopping_admin.data.models.dto

// Created by Jamshid Isoqov an 11/8/2022
data class DriverDto(
    val name: String,
    val password: String,
    val phone: String,
    val active: Boolean = false
)
