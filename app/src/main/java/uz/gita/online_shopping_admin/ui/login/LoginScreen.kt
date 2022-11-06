package uz.gita.online_shopping_admin.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenLoginBinding
import uz.gita.online_shopping_admin.presenter.LoginViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT
import uz.gita.online_shopping_admin.utils.extensions.toast

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val viewBinding: ScreenLoginBinding by viewBinding()

    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnLogin.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
            val login = viewBinding.inputLogin.text.toString()
            val password = viewBinding.inputPassword.text.toString()
            if (login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(login, password)
            } else toast("Fields input required")
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
