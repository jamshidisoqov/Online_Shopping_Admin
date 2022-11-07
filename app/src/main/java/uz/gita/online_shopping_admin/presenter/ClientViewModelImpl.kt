package uz.gita.online_shopping_admin.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.directions.ClientScreenDirection
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.main.clients.ClientViewModel
import uz.gita.online_shopping_admin.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class ClientViewModelImpl @Inject constructor(
    private val repository: Repository,
    private val showed: Showed,
    private val direction: ClientScreenDirection
) : ClientViewModel, ViewModel() {

    override val clientsFlow = MutableStateFlow<List<ClientData>>(emptyList())

    private var clientsList:List<ClientData> = emptyList()

    override fun searchClients(query: String) {
        viewModelScope.launch(Dispatchers.Default){
            clientsList = clientsList.filter {
                it.fullName.startsWith(query)
            }
        }
    }

    override fun navigateToClientDetails(clientData: ClientData) {
        viewModelScope.launch {
            direction.navigateToClientData(clientData)
        }
    }

    override fun getClients() {
        viewModelScope.launch(Dispatchers.IO) {
            showed.setLoading(true)
            repository.getAllClients().collectLatest {
                showed.setLoading(false)
                it.onSuccess {data->
                    clientsFlow.emit(data)
                    clientsList = data
                }.onMessage { message ->
                    showed.setMessage(message)
                }.onError { error ->
                    showed.setError(error.getMessage())
                }

            }

        }
    }
}