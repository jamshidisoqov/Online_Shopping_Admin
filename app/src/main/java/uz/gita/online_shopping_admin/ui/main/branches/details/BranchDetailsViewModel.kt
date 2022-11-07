package uz.gita.online_shopping_admin.ui.main.branches.details

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping_admin.data.models.data.AddressData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchDetailsViewModel {

    val address: SharedFlow<String>

    fun getBranchLocation(address: AddressData)
}