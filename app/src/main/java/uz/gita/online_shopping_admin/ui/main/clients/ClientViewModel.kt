package uz.gita.online_shopping_admin.ui.main.clients

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.ClientData

// Created by Jamshid Isoqov an 11/6/2022
interface ClientViewModel {

    val clientsFlow: StateFlow<List<ClientData>>

    fun searchClients(query: String)

    fun navigateToClientDetails(clientData: ClientData)

    fun getClients()
}