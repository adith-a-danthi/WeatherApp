package com.example.weatherapp

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.DataRepository
import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.di.AppViewModelFactory
import com.example.weatherapp.util.PermissionHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var mPermissionHelper: PermissionHelper
    private lateinit var coordinatorLayout: CoordinatorLayout
    private var mLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as WeatherApp).appComponent.inject(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        Toast.makeText(this, mainViewModel.value.toString(), Toast.LENGTH_LONG).show()

        coordinatorLayout = findViewById(R.id.rootLayout)
        mPermissionHelper = PermissionHelper(this, coordinatorLayout)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

    }

    private fun getLastLocation() {

        if (mPermissionHelper.checkLocationPermission()) {

            val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (isLocationEnabled(locationManager)) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    mLastLocation = task.result

                    if (mLastLocation == null) {

                        val mLocationRequest = LocationRequest()
                        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        mLocationRequest.interval = 0
                        mLocationRequest.fastestInterval = 0
                        mLocationRequest.numUpdates = 1

                        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                        mFusedLocationClient.requestLocationUpdates(
                            mLocationRequest, LocationCallback(), Looper.myLooper()
                        )

                    } else {
                        TODO("Make API Call")
                    }

                }

            } else {
                Toast.makeText(this,"Turn on Location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            mPermissionHelper.requestLocationPermissions()
            getLastLocation()
        }
    }


}
