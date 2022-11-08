package uz.gita.online_shopping_admin.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.databinding.DialogAddCategoryBinding
import uz.gita.online_shopping_admin.utils.extensions.config

// Created by Jamshid Isoqov an 11/8/2022
class AddCategoryDialog : BottomSheetDialogFragment() {

    private var addCategoryClickListener:((String,String)->Unit)?  = null

    fun setAddCategoryClickListener(block:(String,String)->Unit){
        addCategoryClickListener = block
    }

    private lateinit var binding:DialogAddCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogAddCategoryBinding.inflate(inflater)

        dialog?.config(binding)

        binding.btnAddCategory.clicks().onEach {
            val name = binding.inputCategoryName.text.toString()
            val info = binding.inputCategoryInfo.text.toString()
            if (name.isNotEmpty()&&info.isNotEmpty()){
                addCategoryClickListener?.invoke(name,info)
                dismiss()
            }else{
                binding.inputCategoryName.error =  "Fields input required"
                binding.inputCategoryInfo.error =  "Fields input required"
            }
        }.launchIn(lifecycleScope)

        return binding.root
    }


}