package uz.gita.online_shopping_admin.utils.extensions

import android.util.Log
import retrofit2.Response
import uz.gita.online_shopping_admin.data.models.base.BaseResponse
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping_admin.utils.exceptions.AuthorizationException

// Created by Jamshid Isoqov an 10/11/2022


fun <T> Response<BaseResponse<T>>.func(): ResultData<T> {
    val data = this
    if (data.isSuccessful) {
        if (data.body() != null) {
            val body = data.body()!!
            return if (body.status == 200) {
                ResultData.Success(body.data)
            } else
                ResultData.Error(AuthorizationException())
        }
    }
    return ResultData.Error(Throwable(data.errorBody()!!.string()))
}