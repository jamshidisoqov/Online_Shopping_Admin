package uz.gita.online_shopping_admin.ui.main.clients.details

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
import uz.gita.online_shopping_admin.databinding.ScreenClientDetailsBinding
import uz.gita.online_shopping_admin.presenter.ClientDetailViewModelImpl
import uz.gita.online_shopping_admin.ui.main.orders.OrdersAdapter

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class ClientDetailsScreen : Fragment(R.layout.screen_client_details) {

    private val viewBinding: ScreenClientDetailsBinding by viewBinding()

    private val viewModel: ClientDetailViewModel by viewModels<ClientDetailViewModelImpl>()

    private val args: ClientDetailsScreenArgs by navArgs()

    private val adapter: OrdersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        OrdersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = args.clientData

        viewBinding.listOrderByClient.adapter = adapter

        viewBinding.apply {
            tvUserName.text = data.fullName
            tvPhoneNumber.text = data.phoneNumber
        }
        viewModel.orderByClient.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToOrderDetails(it)
        }
        viewModel.getClientOrders(data)
    }

}