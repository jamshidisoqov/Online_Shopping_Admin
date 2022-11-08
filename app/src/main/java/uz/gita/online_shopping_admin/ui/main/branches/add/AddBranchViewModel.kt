package uz.gita.online_shopping_admin.ui.main.branches.add

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping_admin.data.models.dto.BranchDto

// Created by Jamshid Isoqov an 11/8/2022
interface AddBranchViewModel {

    val backSharedFlow:SharedFlow<Unit>

    fun addBranches(branchDto: BranchDto)

}