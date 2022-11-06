package uz.gita.online_shopping_admin.ui.main.orders

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
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenOrdersBinding
import uz.gita.online_shopping_admin.presenter.OrderViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class OrdersScreen : Fragment(R.layout.screen_orders) {

    private val viewBinding: ScreenOrdersBinding by viewBinding()

    private val viewModel: OrderViewModel by viewModels<OrderViewModelImpl>()

    private val adapter: OrdersAdapter by lazy {
        OrdersAdapter()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listOrders.adapter = adapter

        viewModel.orderByFilter.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.imageFilter.setOnClickListener {
            viewModel.navigateToFilter()
        }

        adapter.setItemClickListener {
            viewModel.navigateToOrderDetails(it)
        }
        adapter.setClientItemClickListener {
            //TODO navigate to client
        }
        viewModel.getOrders()

        viewBinding.inputSearch.textChanges()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewModel.searchOrder(it.toString())
            }.launchIn(lifecycleScope)
    }


}