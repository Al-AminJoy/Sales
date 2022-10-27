package com.alamin.sales.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.alamin.sales.R
import com.alamin.sales.SalesApplication
import com.alamin.sales.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    companion object {
        const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private var locationManager : LocationManager? = null

    private lateinit var binding: ActivityMainBinding
    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val component = (this.applicationContext as SalesApplication).appComponent
        component.injectMain(this)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?


        setContentView(binding.root)
        getLocationData()
        setupActionBarWithNavController(findNavController(R.id.fragment));
    }



    override fun onRestart() {
        super.onRestart()
        getLocationData()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment);
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    private fun getLocationData() {
        if (checkPermission()) {
            if (isLocationEnabled()) {

                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        latitude = location.latitude
                        longitude = location.longitude
                        Log.d(TAG, "onLocationChanged: Network $latitude $longitude")
                    }
                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                })


                locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        latitude = location.latitude
                        longitude = location.longitude
                        Log.d(TAG, "onLocationChanged: GPS $latitude $longitude")
                    }
                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                })

            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.turn_on_location),
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermission();
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationData()
            } else {
                val isNeverFine =
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                val isNeverCoarse =
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                val isneverBackground =
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                if (!isNeverCoarse && !isNeverFine && !isneverBackground) {
                    showAlertDialog(true)
                } else {
                    showAlertDialog(false)
                }

            }
        }
    }

    private fun manualPermission() {
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        })
    }

    private fun showAlertDialog(isPermanent: Boolean) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.permission_title))
            .setMessage(getString(R.string.permission_message))
            .setCancelable(false)
            .setPositiveButton(
                "OK"
            ) { dialog, which ->
                if (isPermanent) manualPermission() else requestPermission()
            }
            .create().show()
    }

    fun findLocation(): Array<Double> {
        return arrayOf(latitude, longitude)
    }
}