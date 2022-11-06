package uz.gita.online_shopping_admin.ui.main.orders

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping.utils.extensions.getOrderName
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.data.models.data.OrderData
import uz.gita.online_shopping_admin.databinding.ListItemOrdersBinding
import uz.gita.online_shopping_admin.utils.extensions.inflate

// Created by Jamshid Isoqov an 11/6/2022
class OrdersAdapter : ListAdapter<OrderData, OrdersAdapter.ViewHolder>(itemCallback) {


    private var itemClickListener: ((OrderData) -> Unit)? = null

    fun setItemClickListener(block: (OrderData) -> Unit) {
        itemClickListener = block
    }

    private var clientItemClickListener: ((ClientData) -> Unit)? = null

    fun setClientItemClickListener(block: (ClientData) -> Unit) {
        clientItemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    itemClickListener?.invoke(getItem(absoluteAdapterPosition))
                }
                tvClientName.setOnClickListener {
                    clientItemClickListener?.invoke(getItem(absoluteAdapterPosition).createdBy)
                }
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvOrderName.text = data.number.getOrderName()
                tvClientName.text = data.createdBy.fullName
                tvOrderDate.text = data.createdDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemOrdersBinding.bind(parent.inflate(R.layout.list_item_orders))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private var itemCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean {
        return oldItem.type == newItem.type && oldItem.status == newItem.status
    }

}