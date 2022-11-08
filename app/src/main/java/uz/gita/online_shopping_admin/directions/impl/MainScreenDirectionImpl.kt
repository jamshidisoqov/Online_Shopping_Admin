package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.directions.MainScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.MainScreenDirections
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenDirection {
    override suspend fun navigateToOrders() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToOrdersScreen())
    }

    override suspend fun navigateToFinance() {
        //TODO navigate to finance navigator.navigateTo(MainScreenDirections.)
    }

    override suspend fun navigateToClients() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToClientsScreen())
    }

    override suspend fun navigateToProducts() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToProductsScreen())
    }

    override suspend fun navigateToBranches() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToBranchesScreen())
    }

    override suspend fun navigateToDrivers() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToDriversScreen())
    }
}