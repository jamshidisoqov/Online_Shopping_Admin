package uz.gita.online_shopping_admin.data.models.dto

import uz.gita.online_shopping_admin.data.models.data.AddressData

// Created by Jamshid Isoqov an 11/8/2022
data class BranchDto(
    val id: Long,
    val way: String,
    val address: AddressData,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val schedule: String
)