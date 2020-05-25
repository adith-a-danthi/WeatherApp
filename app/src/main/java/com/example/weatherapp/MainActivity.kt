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
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.source.remote.api.response.WeatherLocation
import com.example.weatherapp.util.PermissionHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var mPermissionHelper: PermissionHelper
    // private lateinit var coordinatorLayout: CoordinatorLayout
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
//                Log.d("LOCATION", "it is null")
                getLastLocation()
            } else {
//                Log.d("LOCATION", it.toString())
                updateViews(it)
            }
        })
    }

    private fun updateViews(weatherLocation: WeatherLocation) {
        val currentHour = SimpleDateFormat("HH", Locale.getDefault()).format(Date()).toInt()
        if (currentHour in 20..24 || currentHour in 0..5)
            rootLayout.background = getDrawable(R.drawable.night_gradient)
        else if (currentHour in 6..8 || currentHour in 17..19)
            rootLayout.background = getDrawable(R.drawable.evening_gradient)
        else
            rootLayout.background = getDrawable(R.drawable.morning_gradient)


        city_tv.text = weatherLocation.name
        country_tv.text = weatherLocation.sys.country
        status.text = weatherLocation.weather[0].description.capitalize()
        temp_tv.text = ("%.0f".format(weatherLocation.main.temp - 273.15))

        val formatter = SimpleDateFormat("HH:mm a", Locale.getDefault())
        sunrise_time.text = formatter.format(Date(weatherLocation.sys.sunrise * 1000))
        sunset_time.text = formatter.format(Date(weatherLocation.sys.sunset * 1000))

        wind_speed.text = weatherLocation.wind.speed.toString()
        humidity.text = weatherLocation.main.humidity.toString()
        pressure.text = weatherLocation.main.pressure.toString()

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
