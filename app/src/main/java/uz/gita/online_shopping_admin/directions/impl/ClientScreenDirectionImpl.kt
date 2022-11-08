package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.directions.ClientScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.clients.ClientsScreenDirections
import javax.inject.Inject

class ClientScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : ClientScreenDirection {
    override suspend fun navigateToClientData(clientData: ClientData) {
        navigator.navigateTo(
            ClientsScreenDirections.actionClientsScreenToClientDetailsScreen(
                clientData
            )
        )
    }
}