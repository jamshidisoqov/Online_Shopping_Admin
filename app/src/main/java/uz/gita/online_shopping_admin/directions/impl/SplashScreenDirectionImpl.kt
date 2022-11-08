package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.directions.SplashScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.splash.SplashScreenDirections
import javax.inject.Inject

class SplashScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}