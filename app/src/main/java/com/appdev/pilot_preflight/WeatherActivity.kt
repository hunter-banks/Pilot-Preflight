package com.appdev.pilot_preflight

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class WeatherActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var nearbyWx: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        nearbyWx = findViewById(R.id.nearby_weather_data)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation { locationList ->
            if (locationList != null) {
                val latitude = locationList[0]
                val longitude = locationList[1]
                // Make API request in a coroutine with IO dispatcher
                GlobalScope.launch(Dispatchers.IO) {
                    val apiUrl = "https://aviationweather.gov/api/data/metar?bbox=${latitude-.3}%2C${longitude-.3}%2C${latitude+.3}%2C${longitude+.3}"
                    val apiResponse = makeApiRequest(apiUrl)

                    // Handle the API response on the main thread
                    launch(Dispatchers.Main) {
                        if (apiResponse != null) {
                            handleApiResponse(apiResponse)
                        }
                    }
                }
            } else {
                // Handle the case where location is null or permission is not granted
                nearbyWx.text = "Unable to get location."
                Log.d("Coor","Unable to get location.")
            }
        }
    }
    private fun makeApiRequest(apiUrl: String): String? {
        return try {
            val url = URL(apiUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("accept", "*/*")

            val responseCode = connection.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val result = StringBuilder()

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line).append("\n\n")
                }

                result.toString()
            } else {
                Log.e("API Request", "Non-OK response code: $responseCode")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun handleApiResponse(apiResponse: String) {
        nearbyWx.text = apiResponse
    }

    private fun getLocation(callback: (List<Double>?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions if not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            // Permissions are already granted, proceed to get location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val locationList = listOf(latitude, longitude)
                        callback(locationList)
                    } else {
                        callback(null) // Handle the case where location is null
                    }
                }
        }
    }
}