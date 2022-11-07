package uz.gita.online_shopping_admin.ui.main.drivers

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.DriverData

// Created by Jamshid Isoqov an 11/7/2022
interface DriverViewModel {

    val driversFlow: StateFlow<List<DriverData>>

    val openDriverDelete: SharedFlow<DriverData>

    fun getDriver()

    fun navigateToDriverDetail(driverData: DriverData)

    fun driverDeleteClick(driverData: DriverData)

    fun deleteDriver(driverData: DriverData)

    fun searchDriver(query:String)

}