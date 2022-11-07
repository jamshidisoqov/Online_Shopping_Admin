package uz.gita.online_shopping_admin.ui.main.products.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenProductDetailBinding
import uz.gita.online_shopping_admin.utils.extensions.getFinanceType

// Created by Jamshid Isoqov an 11/5/2022
class ProductDetailsScreen : Fragment(R.layout.screen_product_detail) {

    private val viewBinding: ScreenProductDetailBinding by viewBinding()

    private val args: ProductDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val productData = args.productData

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewBinding.apply {
            tvProductName.text = productData.name
            tvDescription.text = productData.description
            tvProductCategoryName.text = productData.name
            Glide.with(requireContext())
                .load(productData.imageUrl)
                .placeholder(R.drawable.place)
                .into(imageFood)
            btnToBasket.text = productData.price.getFinanceType()
        }
    }
}