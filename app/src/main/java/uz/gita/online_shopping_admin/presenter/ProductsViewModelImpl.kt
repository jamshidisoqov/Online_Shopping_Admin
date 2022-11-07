package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.CategoryData
import uz.gita.online_shopping_admin.data.models.data.ProductData
import uz.gita.online_shopping_admin.directions.ProductsScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.products.ProductsViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class ProductsViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: ProductsScreenDirection
) : ProductsViewModel, ViewModel() {

    override val categoriesFlow = MutableStateFlow<List<CategoryData>>(emptyList())

    override val productsFlow = MutableStateFlow<List<ProductData>>(emptyList())

    private var products: List<ProductData> = emptyList()

    override fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            showed.setLoading(true)

            repository.getAllCategories().collectLatest {
                it.onSuccess { data ->
                    categoriesFlow.emit(data)
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }

            repository.getAllProducts().collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    productsFlow.emit(data)
                    products = data
                }.onError { error ->
                    showed.setError(error.getMessage())
                }.onMessage { message ->
                    showed.setMessage(message)
                }

            }
        }
    }

    override fun categoryItemClick(categoryData: CategoryData, selectedPos: Int) {
        viewModelScope.launch {
            if (selectedPos == -1) {
                productsFlow.emit(products)
            } else {
                val l = products.filter {
                    it.category!!.id == categoryData.id
                }
                productsFlow.emit(l)
            }
        }
    }

    override fun openProductDetailsScreen(productData: ProductData) {
        viewModelScope.launch {
            direction.navigateToProductDetail(productData)
        }
    }

    override fun searchClicked() {
        viewModelScope.launch {
            direction.navigateToSearch()
        }
    }
}