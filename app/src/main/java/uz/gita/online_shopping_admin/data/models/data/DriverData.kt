package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.online_shopping_admin.data.models.base.BaseData
import uz.gita.online_shopping_admin.data.models.dto.DriverDto

// Created by Jamshid Isoqov an 11/7/2022
@Parcelize
data class DriverData(
    val name: String,
    val active: Boolean,
    val password: String,
    val phone: String
) : BaseData(), Parcelable {
    fun toDriverDto() = DriverDto(name, password, phone, active)
}
