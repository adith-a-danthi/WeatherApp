package com.example.weatherapp.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

private const val PERMISSION_LOCATION_ID = 0

class PermissionHelper(val activity: Activity, val view: View) {

    private val permission = Manifest.permission.ACCESS_FINE_LOCATION
    private val permissionExpalnationString: String = "Location Permission required to determine current location"

    private fun requestPermissions(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            showRationale()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                PERMISSION_LOCATION_ID
            )
        }
    }

    private fun showRationale() {
        Snackbar.make(
            view,
            permissionExpalnationString,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Grant Permission") {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                PERMISSION_LOCATION_ID
            )
        }.show()
    }

    private fun checkPermission(): Boolean{
        val locationPermission = ActivityCompat.checkSelfPermission(activity, permission)
        return (locationPermission == PackageManager.PERMISSION_GRANTED)
    }

}