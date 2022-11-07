package uz.gita.online_shopping_admin.ui.main.clients

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
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenClientsBinding
import uz.gita.online_shopping_admin.presenter.ClientViewModelImpl
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class ClientsScreen : Fragment(R.layout.screen_clients) {

    private val viewBinding: ScreenClientsBinding by viewBinding()

    private val viewModel: ClientViewModel by viewModels<ClientViewModelImpl>()

    private val adapter: ClientsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ClientsAdapter()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listClients.adapter = adapter

        viewBinding.imageBack.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                findNavController().navigateUp()
            }.launchIn(lifecycleScope)

        viewModel.clientsFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToClientDetails(it)
        }
        viewBinding.inputSearch.textChanges()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewModel.searchClients(it.toString())
            }.launchIn(lifecycleScope)
        viewModel.getClients()
    }


}