package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.directions.LoginScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.login.LoginViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: LoginScreenDirection
) : LoginViewModel, ViewModel() {
    override fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(login, password).onSuccess {
                repository.setToken(it)
                showed.setToast("Successfully login")
                direction.navigateToMain()
            }.onError { error ->
                showed.setError(error.getMessage())
            }.onMessage { message ->
                showed.setMessage(message)
            }
        }
    }
}