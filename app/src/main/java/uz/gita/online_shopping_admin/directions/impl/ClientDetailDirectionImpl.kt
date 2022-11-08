package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.directions.ClientDetailDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.clients.details.ClientDetailsScreenDirections
import javax.inject.Inject

class ClientDetailDirectionImpl @Inject constructor(
    private val navigator: Navigator
): ClientDetailDirection {
    override suspend fun navigateToOrderDetails(orderData: OrderData) {
        navigator.navigateTo(ClientDetailsScreenDirections.actionClientDetailsScreenToOrderDetailScreen(orderData))
    }
}