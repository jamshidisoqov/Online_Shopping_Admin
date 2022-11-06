package uz.gita.online_shopping_admin.directions

// Created by Jamshid Isoqov an 11/5/2022
interface MainScreenDirection {

    suspend fun navigateToOrders()

    suspend fun navigateToFinance()

    suspend fun navigateToClients()

    suspend fun navigateToProducts()

    suspend fun navigateToBranches()

    suspend fun navigateToDrivers()

}