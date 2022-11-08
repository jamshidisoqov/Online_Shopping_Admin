package uz.gita.online_shopping_admin.ui.main.clients

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.ClientData
import uz.gita.online_shopping_admin.databinding.ListItemClientBinding
import uz.gita.online_shopping_admin.utils.extensions.inflate

// Created by Jamshid Isoqov an 11/6/2022
class ClientsAdapter : ListAdapter<ClientData, ClientsAdapter.ViewHolder>(itemClientCallback) {

    private var itemClickListener: ((ClientData) -> Unit)? = null

    fun setItemClickListener(block: (ClientData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemClientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)

            binding.apply {
                tvClientName.text = data.fullName
                tvClientPhone.text = data.phoneNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemClientBinding.bind(parent.inflate(R.layout.list_item_client))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}


private var itemClientCallback = object : DiffUtil.ItemCallback<ClientData>() {
    override fun areItemsTheSame(oldItem: ClientData, newItem: ClientData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ClientData, newItem: ClientData): Boolean =
        oldItem.fullName == newItem.fullName && oldItem.active == newItem.active && oldItem.phoneNumber == newItem.phoneNumber

}