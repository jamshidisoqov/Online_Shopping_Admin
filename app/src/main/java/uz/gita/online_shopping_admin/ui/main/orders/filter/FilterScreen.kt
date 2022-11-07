package uz.gita.online_shopping_admin.ui.main.orders.filter

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.online_shopping_admin.R
import uz.gita.online_shopping_admin.databinding.ScreenFilterBinding
import uz.gita.online_shopping_admin.utils.Filter
import uz.gita.online_shopping_admin.utils.enums.FilterStatus
import uz.gita.online_shopping_admin.utils.enums.FilterType
import uz.gita.online_shopping_admin.utils.extensions.getDate

// Created by Jamshid Isoqov an 11/5/2022
@AndroidEntryPoint
class FilterScreen : Fragment(R.layout.screen_filter) {

    private val viewBinding: ScreenFilterBinding by viewBinding()

    private lateinit var typeList: ArrayList<TextView>

    private lateinit var statusList: ArrayList<TextView>

    private var selectedType = 0

    private var selectedStatus = 0
    private val currentTime: Long by lazy {
        System.currentTimeMillis()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        typeList = ArrayList()

        statusList = ArrayList()

        loadView()

        Filter.typeStateFlow.onEach {
            updateTypeNotSelected()
            selectedType = it.ordinal
            updateTypeSelected()
        }.launchIn(lifecycleScope)

        Filter.statusStateFlow.onEach {
            updateStatusNotSelected()
            selectedStatus = it.ordinal
            updateStatusSelected()
        }.launchIn(lifecycleScope)

        Filter.dateStateFlow.onEach {
            viewBinding.tvStartDate.text = it.first.getDate()
            viewBinding.tvEndDate.text = it.second.getDate()
        }.launchIn(lifecycleScope)

        viewBinding.tvStartDate.setOnClickListener {
            openRangeCalendar()
        }
        viewBinding.tvEndDate.setOnClickListener {
            openRangeCalendar()
        }
        viewBinding.btnFilter.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun openRangeCalendar() {
        val dialog =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Choose data range")
                .build()
        dialog.addOnPositiveButtonClickListener {
            lifecycleScope.launch {
                Filter.dateStateFlow.emit(
                    Pair(
                        it.first ?: (currentTime - 86400),
                        it.second ?: currentTime
                    )
                )
            }
        }
        dialog.show(childFragmentManager, "range data")
    }

    private fun updateTypeNotSelected() {
        viewBinding.containerType.apply {
            (getChildAt(selectedType) as TextView).apply {
                setBackgroundResource(R.drawable.bg_search_view)
                setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun updateTypeSelected() {
        viewBinding.containerType.apply {
            (getChildAt(selectedType) as TextView).apply {
                setBackgroundResource(R.drawable.bg_app_btn)
                setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }

    private fun updateStatusNotSelected() {
        viewBinding.containerStatus.apply {
            (getChildAt(selectedType) as TextView).apply {
                setBackgroundResource(R.drawable.bg_search_view)
                setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun updateStatusSelected() {
        viewBinding.containerStatus.apply {
            (getChildAt(selectedType) as TextView).apply {
                setBackgroundResource(R.drawable.bg_app_btn)
                setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }

    private fun loadView() {
        val typeCount = viewBinding.containerType.childCount
        for (i in 0 until typeCount) {
            val textView = viewBinding.containerType.getChildAt(i) as TextView
            textView.tag = i
            textView.setOnClickListener {
                lifecycleScope.launch {
                    Filter.typeStateFlow.emit(FilterType.values()[it.tag.toString().toInt()])
                }
            }
            typeList.add(textView)
        }
        val statusCount = viewBinding.containerStatus.childCount
        for (i in 0 until statusCount) {
            val textView = viewBinding.containerStatus.getChildAt(i) as TextView
            textView.tag = i
            textView.setOnClickListener {
                lifecycleScope.launch {
                    Filter.statusStateFlow.emit(FilterStatus.values()[it.tag.toString().toInt()])
                }
            }
            statusList.add(textView)

        }
    }


}