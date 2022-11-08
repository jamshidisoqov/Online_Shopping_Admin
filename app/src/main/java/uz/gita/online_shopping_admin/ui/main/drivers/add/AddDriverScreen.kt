package uz.gita.online_shopping_admin.ui.main.drivers.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.dto.DriverDto
import uz.gita.online_shopping_admin.databinding.ScreenAddDriverBinding
import uz.gita.online_shopping_admin.presenter.AddDriverViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT
import uz.gita.online_shopping_admin.utils.extensions.toast

// Created by Jamshid Isoqov an 11/8/2022
@AndroidEntryPoint
class AddDriverScreen : Fragment(R.layout.screen_add_driver) {


    private val viewModel: AddDriverViewModel by viewModels<AddDriverViewModelImpl>()

    private val viewBinding: ScreenAddDriverBinding by viewBinding()


    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAddDriver.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewBinding.apply {
                    val name = inputName.text.toString()
                    val phone = inputPhone.text.toString()
                    val password = inputPassword.text.toString()
                    if (name.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.addDriver(DriverDto(name, password, phone))
                    } else toast("Fields input required")
                }

            }.launchIn(lifecycleScope)

        viewModel.backSharedFlow.onEach {
            findNavController().navigateUp()
        }.launchIn(lifecycleScope)

    }


}