package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov an 10/9/2022

@Parcelize
data class BranchData(
    val id: Long,
    val way: String,
    val address: AddressData,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val schedule: String
) : Parcelable
