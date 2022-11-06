package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.directions.MainScreenDirection
import uz.gita.online_shopping_admin.ui.main.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val direction: MainScreenDirection
) : MainViewModel, ViewModel() {
    override fun navigateToOrders() {
        viewModelScope.launch {
            direction.navigateToOrders()
        }
    }

    override fun navigateToFinance() {
        viewModelScope.launch {
            direction.navigateToFinance()
        }
    }

    override fun navigateToClients() {
        viewModelScope.launch {
            direction.navigateToClients()
        }
    }

    override fun navigateToProducts() {
        viewModelScope.launch {
            direction.navigateToProducts()
        }
    }

    override fun navigateToBranches() {
        viewModelScope.launch {
            direction.navigateToBranches()
        }
    }

    override fun navigateToDrivers() {
        viewModelScope.launch {
            direction.navigateToDrivers()
        }
    }
}