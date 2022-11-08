package uz.gita.online_shopping_admin.ui.main.drivers.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenDriverDetailsBinding
import uz.gita.online_shopping_admin.presenter.DriverDetailViewModelImpl
import uz.gita.online_shopping_admin.ui.main.orders.OrdersAdapter

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class DriverDetailsScreen : Fragment(R.layout.screen_driver_details) {

    private val viewBinding: ScreenDriverDetailsBinding by viewBinding()

    private val viewModel: DriverDetailViewModel by viewModels<DriverDetailViewModelImpl>()

    private val args: DriverDetailsScreenArgs by navArgs()

    private val adapter: OrdersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        OrdersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listOrderByDriver.adapter = adapter

        val data = args.driverData

        viewBinding.apply {
            tvDriverName.text = data.name
            tvPhoneNumber.text = data.phone
        }

        viewModel.orderByDrivers.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToOrderDetail(it)
        }

        viewModel.getAllOrdersByDriver(data)

    }


}