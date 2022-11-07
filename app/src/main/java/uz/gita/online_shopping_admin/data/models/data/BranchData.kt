package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.online_shopping_admin.data.models.base.BaseData

// Created by Jamshid Isoqov an 10/9/2022

@Parcelize
data class BranchData(
    val way: String,
    val address: AddressData,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val schedule: String
) : Parcelable,BaseData()
