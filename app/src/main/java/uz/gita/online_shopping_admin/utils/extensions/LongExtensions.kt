package uz.gita.online_shopping_admin.utils.extensions

import java.util.*

// Created by Jamshid Isoqov an 10/13/2022


fun Long.getOrderName() = "Order N-$this"

fun Long.getDate(): String {
    return getCurrentDate(Date(this))
}