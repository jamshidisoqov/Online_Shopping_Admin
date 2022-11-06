package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.directions.MainScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenDirection {
    override suspend fun navigateToOrders() {
        //TODO navigate to ...
    }

    override suspend fun navigateToFinance() {
        //TODO navigate to ...
    }

    override suspend fun navigateToClients() {
        //TODO navigate to ...
    }

    override suspend fun navigateToProducts() {
        //TODO navigate to ...
    }

    override suspend fun navigateToBranches() {
        //TODO navigate to ...
    }

    override suspend fun navigateToDrivers() {
        //TODO navigate to ...
    }
}