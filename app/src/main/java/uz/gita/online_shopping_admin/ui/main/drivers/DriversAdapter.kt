package uz.gita.online_shopping_admin.ui.main.drivers

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.DriverData
import uz.gita.online_shopping_admin.databinding.ListItemDriversBinding
import uz.gita.online_shopping_admin.utils.extensions.inflate

// Created by Jamshid Isoqov an 11/7/2022
class DriversAdapter : ListAdapter<DriverData, DriversAdapter.ViewHolder>(itemDriverCallback) {

    private var itemClickListener: ((DriverData) -> Unit)? = null

    private var itemDeleteListener: ((DriverData) -> Unit)? = null

    private var itemEditListener: ((DriverData) -> Unit)? = null

    fun setItemClickListener(block: (DriverData) -> Unit) {
        itemClickListener = block
    }

    fun setItemDeleteListener(block: (DriverData) -> Unit) {
        itemDeleteListener = block
    }

    fun setItemEditListener(block: (DriverData) -> Unit) {
        itemEditListener = block
    }

    inner class ViewHolder(private val binding: ListItemDriversBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    itemClickListener?.invoke(getItem(absoluteAdapterPosition))
                }
                imageEditDriver.setOnClickListener {
                    itemEditListener?.invoke(getItem(absoluteAdapterPosition))
                }
                imageDeleteDriver.setOnClickListener {
                    itemDeleteListener?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }


        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvDriverName.text = data.name
                tvClientPhone.text = data.phone
                tvDriverPassword.text = data.password
                driverStatus.setBackgroundResource(
                    if (data.active) R.drawable.bg_driver_active
                    else R.drawable.bg_driver_in_active
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemDriversBinding.bind(parent.inflate(R.layout.list_item_drivers))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private var itemDriverCallback = object : DiffUtil.ItemCallback<DriverData>() {
    override fun areItemsTheSame(oldItem: DriverData, newItem: DriverData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DriverData, newItem: DriverData): Boolean =
        oldItem.active == newItem.active && oldItem.name == newItem.name
}