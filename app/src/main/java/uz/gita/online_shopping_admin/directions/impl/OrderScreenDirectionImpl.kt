package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.OrderScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.orders.OrdersScreenDirections
import javax.inject.Inject

class OrderScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : OrderScreenDirection {

    override suspend fun navigateToFilter() {
      navigator.navigateTo(OrdersScreenDirections.actionOrdersScreenToFilterScreen())
    }

    override suspend fun navigateToOrderDetails(orderData: OrderData) {
        navigator.navigateTo(OrdersScreenDirections.actionOrdersScreenToOrderDetailScreen(orderData))
    }
}