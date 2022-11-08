package uz.gita.online_shopping_admin.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.online_shopping_admin.databinding.DialogConfirmBinding
import uz.gita.online_shopping_admin.utils.extensions.config

// Created by Jamshid Isoqov an 10/12/2022
class ConfirmDialog(ctx: Context, private val message: String = "") : Dialog(ctx) {

    private var confirmClickListener: (() -> Unit)? = null

    private lateinit var binding: DialogConfirmBinding

    fun setConfirmClickListener(block: () -> Unit) {
        confirmClickListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogConfirmBinding.inflate(layoutInflater)
        config(viewBinding = binding)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        if (message.isNotEmpty()) {
            binding.tvMessage.text = message
        }
        binding.btnConfirm.setOnClickListener {
            confirmClickListener?.invoke()
            dismiss()
        }
    }

}