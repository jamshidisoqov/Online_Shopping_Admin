package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.directions.LoginScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.login.LoginScreenDirections
import javax.inject.Inject

class LoginScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
    }
}