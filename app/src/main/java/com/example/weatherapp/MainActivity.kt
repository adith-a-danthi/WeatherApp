package com.example.weatherapp

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.util.PermissionHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

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

        // coordinatorLayout = findViewById(R.id.rootLayout) - replaced by Kotlinx library
        mPermissionHelper = PermissionHelper(this, rootLayout)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        mainViewModel.apiData.observe(this, Observer {
            if (it == null){
                Log.d("LOCATION", "it is null")
                getLastLocation()
            } else {
                Log.d("LOCATION", it.toString())
            }
        })
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
                        mainViewModel.getWeather(mLastLocation!!.latitude.toFloat(), mLastLocation!!.longitude.toFloat())

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
