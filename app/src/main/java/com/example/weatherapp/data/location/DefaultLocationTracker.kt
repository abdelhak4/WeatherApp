package com.example.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.content.getSystemService
import com.example.weatherapp.domain.location.LocationTracker
import javax.inject.Inject
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val application: Application,
    private val locationClient: FusedLocationProviderClient,
) : LocationTracker {
    override suspend fun getLocation(): Location? {
        val isFineLocationPermissionGrant = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val isCoarseLocationPermissionGrant = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )

        if (!isFineLocationPermissionGrant || !isCoarseLocationPermissionGrant || !isGpsEnabled)
            return null
        return suspendCancellableCoroutine { continuation ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful)
                        continuation.resume(result)
                    else
                        continuation.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { continuation.resume(it) }
                addOnFailureListener { continuation.resume(null) }
                addOnCanceledListener { continuation.cancel() }
            }
        }
    }
}