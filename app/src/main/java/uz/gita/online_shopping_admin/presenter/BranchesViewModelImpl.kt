package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.BranchData
import uz.gita.online_shopping_admin.directions.BranchesDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.branches.BranchesViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class BranchesViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val direction: BranchesDirection,
    private val showed: Showed
) : BranchesViewModel, ViewModel() {


    override val branchesFlow = MutableStateFlow<List<BranchData>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getAllBranches().collectLatest {
                showed.setLoading(false)
                it.onMessage { message ->
                    showed.setMessage(message)
                }
                it.onSuccess { list ->
                    branchesFlow.emit(list)
                }
                it.onError { error ->
                    showed.setMessage(error.getMessage())
                }
            }
        }
    }

    override fun navigateToBranchDetails(branchData: BranchData) {
        viewModelScope.launch {
            direction.navigateToBranchesDetails(branchData)
        }
    }

    override fun navigateToAddBranch() {
        viewModelScope.launch {
            direction.navigateToAddBranch()
        }
    }
}