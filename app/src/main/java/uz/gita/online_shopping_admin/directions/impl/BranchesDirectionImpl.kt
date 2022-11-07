package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.BranchData
import uz.gita.online_shopping_admin.directions.BranchesDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import uz.gita.online_shopping_admin.ui.main.branches.BranchesScreenDirections
import javax.inject.Inject

class BranchesDirectionImpl @Inject constructor(private val navigator: Navigator) :
    BranchesDirection {
    override suspend fun navigateToBranchesDetails(branchData: BranchData) {
        navigator.navigateTo(
            BranchesScreenDirections.actionBranchesScreenToBranchDetailsScreen(
                branchData
            )
        )
    }
}