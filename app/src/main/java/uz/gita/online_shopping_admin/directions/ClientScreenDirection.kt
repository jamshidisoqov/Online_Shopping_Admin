package uz.gita.online_shopping_admin.directions

import uz.gita.online_shopping_admin.data.models.data.ClientData

// Created by Jamshid Isoqov an 11/6/2022
interface ClientScreenDirection {

    suspend fun navigateToClientData(clientData: ClientData)

}