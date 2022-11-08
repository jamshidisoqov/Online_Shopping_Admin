package uz.gita.online_shopping_admin.data.models.dto

import uz.gita.online_shopping_admin.data.models.data.AddressData

// Created by Jamshid Isoqov an 11/8/2022
data class BranchDto(
    val name: String,
    val address: AddressData,
    val way: String,
    val phone: String,
    val schedule: String,
    val imageUrl: String = "https://maxway.uz/images/Rectangle/about.png"
)