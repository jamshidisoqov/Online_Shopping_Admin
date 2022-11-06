package uz.gita.online_shopping_admin.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenMainBinding
import uz.gita.online_shopping_admin.presenter.MainViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT

// Created by Jamshid Isoqov an 11/5/2022
@HiltViewModel
class MainScreen : Fragment(R.layout.screen_main) {

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    private val viewBinding: ScreenMainBinding by viewBinding()


    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            containerOrders.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToOrders()
            }.launchIn(lifecycleScope)

            containerFinance.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToFinance()
            }.launchIn(lifecycleScope)

            containerClients.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToClients()
            }.launchIn(lifecycleScope)

            containerProducts.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToProducts()
            }.launchIn(lifecycleScope)

            containerBranches.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToBranches()
            }.launchIn(lifecycleScope)

            containerDrivers.clicks().debounce(DEBOUNCE_TIME_OUT).onEach {
                viewModel.navigateToDrivers()
            }.launchIn(lifecycleScope)

        }

    }

}
