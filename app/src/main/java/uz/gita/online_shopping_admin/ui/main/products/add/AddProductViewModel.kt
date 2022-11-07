package uz.gita.online_shopping_admin.ui.main.products.add

import android.net.Uri
import uz.gita.online_shopping_admin.data.models.data.CategoryData

// Created by Jamshid Isoqov an 11/7/2022
interface AddProductViewModel {

    fun addProduct(categoryData: CategoryData, name: String, price: Double, imgUri: Uri)

}