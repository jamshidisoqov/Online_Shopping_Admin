package uz.gita.online_shopping_admin.ui.main.branches

import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping_admin.data.models.data.BranchData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesViewModel {

    val branchesFlow: StateFlow<List<BranchData>>

    fun navigateToBranchDetails(branchData: BranchData)

    fun navigateToAddBranch()

}