package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.BranchData
import uz.gita.online_shopping_admin.directions.BranchesDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.ui.main.branches.BranchesViewModel
import javax.inject.Inject

@HiltViewModel
class BranchesViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val direction: BranchesDirection
) : BranchesViewModel, ViewModel() {

    override val loadingSharedFlow = MutableLiveData<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override val branchesFlow = MutableStateFlow<List<BranchData>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingSharedFlow.postValue(true)
            repository.getAllBranches().collectLatest {
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                it.onSuccess { list ->
                    branchesFlow.emit(list)
                }
                it.onError { error ->
                    errorMessageFlow.emit(error.message ?: "Unknown exception")
                }
                loadingSharedFlow.postValue(false)
            }
        }
    }

    override fun navigateToBranchDetails(branchData: BranchData) {
        viewModelScope.launch {
            direction.navigateToBranchesDetails(branchData)
        }
    }
}