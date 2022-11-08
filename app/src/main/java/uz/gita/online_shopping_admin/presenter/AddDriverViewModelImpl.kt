package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.dto.DriverDto
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.drivers.add.AddDriverViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class AddDriverViewModelImpl @Inject constructor(
    private val showed: Showed,
    private val repository: Repository
) : AddDriverViewModel, ViewModel() {
    override val backSharedFlow = MutableSharedFlow<Unit>()

    override fun addDriver(driverDto: DriverDto) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.addDriver(driverDto).collectLatest {
                showed.setLoading(false)
                it.onSuccess {
                    showed.setToast("Successfully added")
                    backSharedFlow.emit(Unit)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }.onMessage { message ->
                    showed.setMessage(message)
                }
            }
        }
    }
}