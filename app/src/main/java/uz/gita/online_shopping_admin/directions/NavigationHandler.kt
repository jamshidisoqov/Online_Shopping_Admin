package uz.gita.online_shopping_admin.directions

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

typealias NavigationArgs = NavController.() -> Unit

interface NavigationHandler {
    val navigationStack: Flow<NavigationArgs>
}