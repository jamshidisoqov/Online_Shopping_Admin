package uz.gita.online_shopping_admin.ui.main.branches.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.AddressData
import uz.gita.online_shopping_admin.data.models.dto.BranchDto
import uz.gita.online_shopping_admin.databinding.ScreenAddBranchesBinding
import uz.gita.online_shopping_admin.presenter.AddBranchViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT
import uz.gita.online_shopping_admin.utils.extensions.toast

// Created by Jamshid Isoqov an 11/8/2022
@AndroidEntryPoint
class AddBranchesScreen : Fragment(R.layout.screen_add_branches) {

    private val viewBinding: ScreenAddBranchesBinding by viewBinding()

    private lateinit var addressData: AddressData

    private val viewModel: AddBranchViewModel by viewModels<AddBranchViewModelImpl>()

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.inputAddress
            .clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                navController.navigate(AddBranchesScreenDirections.actionAddBranchesScreenToMapScreen())
            }.launchIn(lifecycleScope)

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<AddressData>("map")
            ?.observe(viewLifecycleOwner) {
                addressData = it
            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("way")
            ?.observe(viewLifecycleOwner) {
                viewBinding.inputAddress.setText(it)
            }

        viewModel.backSharedFlow.onEach {
            navController.navigateUp()
        }.launchIn(lifecycleScope)

        viewBinding.btnAddBranch
            .clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewBinding.apply {
                    val name = inputName.text.toString()
                    val way = inputWay.text.toString()
                    val phone = inputPhone.text.toString()
                    val schedule = inputSchedule.text.toString()
                    if (name.isNotEmpty() && way.isNotEmpty() && phone.isNotEmpty() && schedule.isNotEmpty()) {
                        viewModel.addBranches(BranchDto(name, addressData, way, phone, schedule))
                    } else toast("Fields input required")
                }
            }.launchIn(lifecycleScope)

    }


}