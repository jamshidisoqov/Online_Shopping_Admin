package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.DriverDetailDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import javax.inject.Inject

class DriverDetailDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : DriverDetailDirection {
    override suspend fun navigateToOrderDetails(orderData: OrderData) {
        //navigator.navigateTo()
    }
}