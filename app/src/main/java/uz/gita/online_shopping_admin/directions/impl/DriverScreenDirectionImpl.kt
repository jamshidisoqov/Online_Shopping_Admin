package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.DriverData
import uz.gita.online_shopping_admin.directions.DriverScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.drivers.DriversScreenDirections
import javax.inject.Inject

class DriverScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : DriverScreenDirection {
    override suspend fun navigateToDriverDetail(driverData: DriverData) {
        //navigator.navigateTo(DriversScreenDirections)
    }
}