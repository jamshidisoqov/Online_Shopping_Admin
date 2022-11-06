package uz.gita.online_shopping_admin.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import uz.gita.online_shopping_admin.utils.SharedPreference
import javax.inject.Inject

// Created by Jamshid Isoqov an 11/5/2022
class MySharedPref @Inject constructor(
    ctx: Context,
    sharedPreferences: SharedPreferences
) : SharedPreference(ctx, sharedPreferences) {

    var token: String by Strings("")

}