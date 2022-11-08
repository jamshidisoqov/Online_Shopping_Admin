package uz.gita.online_shopping_admin.ui.main.drivers.add

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping_admin.data.models.dto.DriverDto

// Created by Jamshid Isoqov an 11/8/2022
interface AddDriverViewModel {

    val backSharedFlow:SharedFlow<Unit>

    fun addDriver(driverDto: DriverDto)

}