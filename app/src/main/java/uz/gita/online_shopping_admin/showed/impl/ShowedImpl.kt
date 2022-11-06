package uz.gita.online_shopping_admin.showed.impl

import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.online_shopping_admin.showed.Showed
import javax.inject.Inject

class ShowedImpl @Inject constructor() : Showed {

    override val showedLoadingFlow = MutableSharedFlow<Boolean>()

    override val showedErrorFlow = MutableSharedFlow<String>()

    override val showedMessageFlow = MutableSharedFlow<String>()

    override val toastFlow = MutableSharedFlow<String>()

    override suspend fun setLoading(loading: Boolean) {
        showedLoadingFlow.emit(loading)
    }

    override suspend fun setError(error: String) {
        showedErrorFlow.emit(error)
    }

    override suspend fun setMessage(message: String) {
        showedMessageFlow.emit(message)
    }

    override suspend fun setToast(toast: String) {
        toastFlow.emit(toast)
    }
}