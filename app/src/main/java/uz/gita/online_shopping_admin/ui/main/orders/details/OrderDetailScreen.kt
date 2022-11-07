package uz.gita.online_shopping_admin.ui.main.orders.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping_admin.utils.extensions.getOrderName
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.ProductItem
import uz.gita.online_shopping_admin.data.models.enums.OrderStatus
import uz.gita.online_shopping_admin.data.models.enums.OrderType
import uz.gita.online_shopping_admin.databinding.ListItemOrderProductsBinding
import uz.gita.online_shopping_admin.databinding.ScreenOrderDetailsBinding
import uz.gita.online_shopping_admin.presenter.OrderDetailViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.getFinanceType

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class OrderDetailScreen : Fragment(R.layout.screen_order_details) {

    private val viewBinding: ScreenOrderDetailsBinding by viewBinding()

    private val viewModel: OrderDetailViewModel by viewModels<OrderDetailViewModelImpl>()

    private val args: OrderDetailScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = args.orderData
        viewBinding.tvOderNumber.text = data.id.getOrderName()
        viewBinding.tvOrderType.text = data.type.name
        viewBinding.tvOrderDate.text = data.createdDate
        loadOrders(data.products)
        viewBinding.tvOderStatus.text = data.status.name
        viewBinding.btnOrderStatus.text = OrderStatus.values()[data.status.ordinal + 1].name
        viewModel.address.onEach {
            viewBinding.tvLocation.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewBinding.orderStepView.setStepsNumber(if (data.type == OrderType.SIMPLE) 3 else 4)
        viewBinding.orderStepView.go(data.status.ordinal + 1, true)

        viewModel.orderConfirmedFlow.onEach {
            viewBinding.btnOrderStatus.apply {
                isEnabled = it
                if (it) {
                    text = data.status.name
                    setTextColor(Color.parseColor("#ffffff"))
                } else {
                    text = OrderStatus.DELIVERED.name
                    setTextColor(Color.parseColor("#000000"))
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.setOrderStatus(data.status)

        viewBinding.btnOrderStatus.setOnClickListener {
            viewModel.orderConfirmed(data)
            findNavController().navigateUp()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun loadOrders(list: List<ProductItem>) {
        var summ = 0.0
        for (i in list) {
            summ += i.quantity * i.product?.price!!
            val orderBinding = ListItemOrderProductsBinding.inflate(layoutInflater)
            orderBinding.tvOrderProductNameWithCount.text = "${i.product.name} ${i.quantity}x"
            orderBinding.tvOrderProductPrice.text = i.product.price.getFinanceType()
            viewBinding.productsContainer.addView(orderBinding.root)
        }
        viewBinding.tvOrderAllSum.text = summ.getFinanceType()
    }


}