package uz.gita.online_shopping_admin.presenter

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.CategoryData
import uz.gita.online_shopping_admin.data.models.dto.CategoryDto
import uz.gita.online_shopping_admin.data.models.dto.ProductDto
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.products.add.AddProductViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class AddProductViewModelImpl @Inject constructor(
    private val showed: Showed,
    private val repository: Repository
) : AddProductViewModel, ViewModel() {

    override val backSharedFlow = MutableSharedFlow<Unit>()

    override val categoryFlow = MutableStateFlow<List<CategoryData>>(emptyList())

    override fun getAllCategory() {
        viewModelScope.launch {
            showed.setLoading(true)
            repository.getAllCategories().collectLatest {
                showed.setLoading(false)
                it.onSuccess { data ->
                    categoryFlow.emit(data)
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }
        }
    }

    override fun addCategory(categoryDto: CategoryDto) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.addCategory(categoryDto).collectLatest {
                showed.setLoading(false)
                it.onSuccess {
                    showed.setToast("Successfully added")
                    getAllCategory()
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }
        }
    }

    override fun addProduct(
        categoryData: CategoryData,
        name: String,
        price: Double,
        imgUri: Uri,
        desc: String
    ) {
        viewModelScope.launch {
            showed.setLoading(true)
            repository.uploadImage(imgUri).collectLatest {
                it.onSuccess { data ->
                    repository.addProduct(ProductDto(name, categoryData, data, price, desc))
                        .collectLatest { result ->
                            showed.setLoading(false)
                            result.onSuccess {
                                backSharedFlow.emit(Unit)
                            }.onMessage { message ->
                                showed.setMessage(message)
                            }.onError { error ->
                                showed.setError(error.getMessage())
                            }
                        }
                }.onMessage { message ->
                    showed.setLoading(false)
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setLoading(false)
                    showed.setError(error.getMessage())
                }
            }
        }
    }
}