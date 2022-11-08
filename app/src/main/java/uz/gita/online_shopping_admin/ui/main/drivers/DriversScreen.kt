package uz.gita.online_shopping_admin.ui.main.drivers

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
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenDriversBinding
import uz.gita.online_shopping_admin.presenter.DriverViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT
import uz.gita.online_shopping_admin.utils.extensions.showConfirmDialog

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class DriversScreen : Fragment(R.layout.screen_drivers) {

    private val viewBinding: ScreenDriversBinding by viewBinding()

    private val adapter: DriversAdapter by lazy(LazyThreadSafetyMode.NONE) {
        DriversAdapter()
    }

    private val viewModel: DriverViewModel by viewModels<DriverViewModelImpl>()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listDrivers.adapter = adapter

        viewModel.driversFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToDriverDetail(it)
        }
        adapter.setItemDeleteListener {
            viewModel.driverDeleteClick(it)
        }
        adapter.setItemEditListener {
            viewModel.navigateToDriverDetail(it)
        }

        viewBinding.inputSearch.textChanges()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewModel.searchDriver(it.toString())
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.imageBack.clicks()
            .debounce(DEBOUNCE_TIME_OUT).onEach {
                findNavController().navigateUp()
            }.launchIn(lifecycleScope)

        viewModel.openDriverDelete.onEach {
            showConfirmDialog(resources.getString(R.string.delete_driver)){
                viewModel.deleteDriver(it)
            }
        }.launchIn(lifecycleScope)

        viewModel.getDriver()

    }
}