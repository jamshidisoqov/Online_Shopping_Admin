package uz.gita.online_shopping_admin.data.models.data

import uz.gita.online_shopping_admin.data.models.base.BaseData

// Created by Jamshid Isoqov an 11/7/2022
data class DriverData(
    val name: String,
    val active: Boolean,
    val password: String,
    val phone: String
) : BaseData()
