package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.ProductData
import uz.gita.online_shopping_admin.directions.SearchScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.products.search.SearchViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: SearchScreenDirection
) : SearchViewModel, ViewModel() {

    override val productFlow = MutableStateFlow<List<ProductData>>(emptyList())

    private var products: List<ProductData> = emptyList()


    override fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getAllProducts().collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    productFlow.emit(data)
                    products = data
                }.onError { error ->
                    showed.setError(error.getMessage())
                }.onMessage { message ->
                    showed.setMessage(message)
                }
            }
        }
    }

    override fun navigateToProductDetail(productData: ProductData) {
        viewModelScope.launch {
            direction.navigateToProductDetail(productData)
        }
    }

    override fun searchProducts(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val l = products.filter {
                it.name.startsWith(query)
            }
            productFlow.emit(l)
        }
    }
}