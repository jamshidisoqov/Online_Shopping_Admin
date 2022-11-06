package uz.gita.online_shopping_admin.showed

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 11/5/2022
interface Showed {

    val showedLoadingFlow: SharedFlow<Boolean>

    val showedErrorFlow: SharedFlow<String>

    val showedMessageFlow: SharedFlow<String>

    val toastFlow: SharedFlow<String>


    suspend fun setLoading(loading: Boolean)

    suspend fun setError(error: String)

    suspend fun setMessage(message: String)

    suspend fun setToast(toast: String)

}