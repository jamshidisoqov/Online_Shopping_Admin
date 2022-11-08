package uz.gita.online_shopping_admin.ui.main.branches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenBranchesBinding
import uz.gita.online_shopping_admin.presenter.BranchesViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class BranchesScreen : Fragment(R.layout.screen_branches) {

    private val viewModel: BranchesViewModel by viewModels<BranchesViewModelImpl>()

    private val viewBinding: ScreenBranchesBinding by viewBinding()

    private val adapter: BranchesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BranchesAdapter()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listBranches.adapter = adapter

        viewModel.branchesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToBranchDetails(it)
        }

        viewBinding.fabAddBranch.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewModel.navigateToAddBranch()
            }.launchIn(lifecycleScope)

        viewBinding.imageBack.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                findNavController().navigateUp()
            }.launchIn(lifecycleScope)
    }


}