package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.ProductData
import uz.gita.online_shopping_admin.directions.SearchScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import javax.inject.Inject

class SearchScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SearchScreenDirection {
    override suspend fun navigateToProductDetail(productData: ProductData) {

    }
}