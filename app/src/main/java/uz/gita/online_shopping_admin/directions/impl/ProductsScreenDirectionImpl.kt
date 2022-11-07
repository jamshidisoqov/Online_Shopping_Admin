package uz.gita.online_shopping_admin.directions.impl

import uz.gita.online_shopping_admin.data.models.data.ProductData
import uz.gita.online_shopping_admin.directions.ProductsScreenDirection
import uz.gita.online_shopping_admin.navigation.Navigator
import javax.inject.Inject

class ProductsScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : ProductsScreenDirection {
    override fun navigateToProductDetail(productData: ProductData) {
        TODO("Not yet implemented")
    }

    override fun navigateToSearch() {
        TODO("Not yet implemented")
    }

    override fun navigateToAddProducts() {
        TODO("Not yet implemented")
    }
}