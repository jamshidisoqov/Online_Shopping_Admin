package uz.gita.online_shopping_admin.ui.main.products.search

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
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenSearchProductsBinding
import uz.gita.online_shopping_admin.presenter.SearchViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.screen_search_products) {

    private val viewBinding: ScreenSearchProductsBinding by viewBinding()

    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()

    private val adapter: SearchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchAdapter()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listSearchProducts.adapter = adapter


        viewModel.productFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToProductDetail(it)
        }

        viewModel.getProducts()

        viewBinding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        viewBinding.inputProduct.textChanges()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewModel.searchProducts(it.toString())
            }.launchIn(lifecycleScope)

    }

}