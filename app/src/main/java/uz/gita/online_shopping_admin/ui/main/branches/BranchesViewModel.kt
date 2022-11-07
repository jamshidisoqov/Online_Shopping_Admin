package uz.gita.online_shopping_admin.ui.main.branches

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.BranchData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesViewModel {

    val loadingSharedFlow: LiveData<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    val branchesFlow: StateFlow<List<BranchData>>

    fun navigateToBranchDetails(branchData: BranchData)

}