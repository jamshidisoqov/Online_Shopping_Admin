package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.directions.SplashScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import javax.inject.Inject

class SplashScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        TODO("navigate to main")
    }

    override suspend fun navigateToLogin() {
        TODO("navigate to login")
    }
}