package uz.gita.online_shopping_admin.data.models.data

import uz.gita.online_shopping_admin.data.models.base.BaseData

data class ClientData(
    val active: Boolean,
    val fullName: String,
    val phoneNumber: String
):BaseData()