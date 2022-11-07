package uz.gita.online_shopping_admin.ui.main.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenProductsBinding
import uz.gita.online_shopping_admin.presenter.ProductsViewModelImpl

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class ProductsScreen : Fragment(R.layout.screen_products) {

    private val viewBinding: ScreenProductsBinding by viewBinding()

    private val viewModel: ProductsViewModel by viewModels<ProductsViewModelImpl>()

    private val productAdapter: ProductsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ProductsAdapter()
    }

    private val categoryAdapter: CategoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoryAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listCategories.adapter = categoryAdapter

        viewBinding.listProducts.adapter = productAdapter

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.productsFlow.onEach {
            productAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.categoriesFlow.onEach {
            categoryAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        categoryAdapter.setCategoryListener {
            viewModel.categoryItemClick(it, categoryAdapter.selectedPos)
        }

        productAdapter.setItemClickListener {
            viewModel.openProductDetailsScreen(it)
        }

        viewBinding.tvSearch.setOnClickListener {
            viewModel.searchClicked()
        }

        viewModel.getProducts()

    }

}