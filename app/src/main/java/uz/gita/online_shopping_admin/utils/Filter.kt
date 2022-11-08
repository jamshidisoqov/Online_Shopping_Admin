package uz.gita.online_shopping_admin.utils

import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.online_shopping_admin.utils.enums.FilterStatus
import uz.gita.online_shopping_admin.utils.enums.FilterType
import uz.gita.online_shopping_admin.utils.extensions.getCurrentAndLeftDay

// Created by Jamshid Isoqov an 11/6/2022
object Filter {

    var dateStateFlow: MutableStateFlow<Pair<Long, Long>> =
        MutableStateFlow(getCurrentAndLeftDay(1))

    var typeStateFlow: MutableStateFlow<FilterType> = MutableStateFlow(FilterType.ALL)

    var statusStateFlow: MutableStateFlow<FilterStatus> = MutableStateFlow(FilterStatus.ORDERED)

}