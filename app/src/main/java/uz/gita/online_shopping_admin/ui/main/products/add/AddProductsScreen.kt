package uz.gita.online_shopping_admin.ui.main.products.add

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.data.models.data.CategoryData
import uz.gita.online_shopping_admin.data.models.dto.CategoryDto
import uz.gita.online_shopping_admin.databinding.ScreenAddProductsBinding
import uz.gita.online_shopping_admin.presenter.AddProductViewModelImpl
import uz.gita.online_shopping_admin.ui.dialogs.AddCategoryDialog
import uz.gita.online_shopping_admin.utils.extensions.DEBOUNCE_TIME_OUT
import uz.gita.online_shopping_admin.utils.extensions.MaskWatcherPayment
import uz.gita.online_shopping_admin.utils.extensions.setLocalImage
import uz.gita.online_shopping_admin.utils.extensions.toast

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class AddProductsScreen : Fragment(R.layout.screen_add_products) {

    private val viewModel: AddProductViewModel by viewModels<AddProductViewModelImpl>()

    private val viewBinding: ScreenAddProductsBinding by viewBinding()

    private var imageUri: Uri? = null

    private var lastClicked = 0

    private lateinit var categories: List<CategoryData>

    private val profileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                imageUri = uri
                viewBinding.imageFood.setLocalImage(uri, false)
            }
        }


    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.inputPrice.addTextChangedListener(MaskWatcherPayment(viewBinding.inputPrice))

        viewBinding.imageAddCategory.setOnClickListener {
            val dialog = AddCategoryDialog()
            dialog.setAddCategoryClickListener { s, s2 ->
                viewModel.addCategory(CategoryDto(s, s2))
            }
            dialog.show(childFragmentManager, "add category")
        }

        viewModel.categoryFlow.onEach {
            val adapter =
                ArrayAdapter(requireContext(), R.layout.list_item_spinner, it.map { data ->
                    data.name
                })
            categories = it
            viewBinding.actCategory.adapter = adapter
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.actCategory.setOnItemClickListener { _, _, position, _ ->
            lastClicked = position
        }

        viewBinding.imageFood.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                ImagePicker.with(requireActivity())
                    .crop()
                    .maxResultSize(1080, 1080, true)
                    .provider(ImageProvider.BOTH)
                    .createIntentFromDialog {
                        profileLauncher.launch(it)
                    }
            }.launchIn(lifecycleScope)

        viewBinding.btnAddProduct.clicks()
            .debounce(DEBOUNCE_TIME_OUT)
            .onEach {
                viewBinding.apply {
                    val name = inputName.text.toString()
                    val price = inputPrice.text.toString().toDouble()
                    val info = inputInfo.text.toString()
                    if (imageUri != null) {
                        if (name.isNotEmpty() && price > 0.0 && info.isNotEmpty()) {
                            viewModel.addProduct(
                                categories[lastClicked],
                                name,
                                price,
                                imageUri!!,
                                info
                            )
                        }
                    } else {
                        toast("Please select image")
                    }
                }
            }.launchIn(lifecycleScope)

        viewModel.getAllCategory()

    }


}