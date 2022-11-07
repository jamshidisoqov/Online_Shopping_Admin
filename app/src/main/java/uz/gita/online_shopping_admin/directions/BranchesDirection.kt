package uz.gita.online_shopping_admin.directions

import uz.gita.online_shopping_admin.data.models.data.BranchData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesDirection {

    suspend fun navigateToBranchesDetails(branchData: BranchData)
}