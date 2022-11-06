package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.directions.SplashScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.ui.splash.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val direction: SplashScreenDirection
) : SplashViewModel, ViewModel() {
    override fun navigate() {
        viewModelScope.launch {
            delay(1500)
            if (repository.getToken().isEmpty()) {
                direction.navigateToLogin()
            } else {
                direction.navigateToMain()
            }
        }
    }
}