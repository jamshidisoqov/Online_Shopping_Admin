package uz.gita.online_shopping_admin.utils.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import uz.gita.online_shopping_admin.ui.dialogs.ConfirmDialog
import uz.gita.online_shopping_admin.ui.dialogs.ErrorDialog
import uz.gita.online_shopping_admin.ui.dialogs.MessageDialog

// Created by Jamshid Isoqov an 10/12/2022


const val DEBOUNCE_TIME_OUT: Long = 150L

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.hasPermission(permission: String): Boolean {
    return requireActivity().applicationContext.hasPermission(permission)
}

fun Context.hasPermission(permission: String): Boolean {

    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q
    ) return true

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Fragment.showConfirmDialog(message: String, block: () -> Unit) {
    val dialog = ConfirmDialog(requireContext(), message)
    dialog.setConfirmClickListener {
        block.invoke()
    }
    dialog.show()
}

fun Fragment.isLocationEnabled(): Boolean {
    val locationManager =
        requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}


fun Activity.showErrorDialog(message: String) {
    val dialog = ErrorDialog(this, message)
    dialog.show()
}

fun Activity.showMessageDialog(message: String) {
    val dialog = MessageDialog(this, message)
    dialog.show()
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
