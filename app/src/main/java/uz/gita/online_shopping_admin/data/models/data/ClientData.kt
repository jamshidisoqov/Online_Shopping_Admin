package uz.gita.online_shopping_admin.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.gita.online_shopping_admin.data.models.base.BaseData

@Parcelize
data class ClientData(
    val active: Boolean,
    val fullName: String,
    val phoneNumber: String
) : BaseData(), Parcelable