package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.dto.BranchDto
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.branches.add.AddBranchViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class AddBranchViewModelImpl @Inject constructor(
    private val showed: Showed,
    private val repository: Repository
) : AddBranchViewModel, ViewModel() {

    override val backSharedFlow = MutableSharedFlow<Unit>()

    override fun addBranches(branchDto: BranchDto) {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.addBranch(branchDto).collectLatest {
                showed.setLoading(false)
                it.onSuccess {
                    backSharedFlow.emit(Unit)
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }
            }
        }
    }
}