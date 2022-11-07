package uz.gita.online_shopping_admin.ui.main.products

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.ProductData
import uz.gita.online_shopping_admin.databinding.ListItemProductsBinding
import uz.gita.online_shopping_admin.utils.extensions.getFinanceType
import uz.gita.online_shopping_admin.utils.extensions.inflate

// Created by Jamshid Isoqov an 11/7/2022
class ProductsAdapter : ListAdapter<ProductData, ProductsAdapter.ViewHolder>(itemProductsCallback) {

    private var itemClickListener: ((ProductData) -> Unit)? = null

    fun setItemClickListener(block: (ProductData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvProductName.text = data.name
                tvProductPrice.text = data.price.getFinanceType()
                Glide.with(imageProductsItem.context)
                    .load(data.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(binding.imageProductsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemProductsBinding.bind(parent.inflate(R.layout.list_item_products))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private val itemProductsCallback = object : DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean =
        oldItem.name == newItem.name && oldItem.category == newItem.category && oldItem.price == newItem.price


}