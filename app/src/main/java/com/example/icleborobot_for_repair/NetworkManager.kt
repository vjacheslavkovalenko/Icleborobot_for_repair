package com.example.icleborobot_for_repair

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class NetworkManager (contex : Context) : LiveData<Boolean>(){

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActive() {
        super.onActive()
        checkNetworkConnectivity()
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private var connectivityManager =
        contex.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            postValue(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()

            postValue(false)
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            postValue(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkNetworkConnectivity(){

        val network = connectivityManager.activeNetwork
        if (network == null)
            postValue(false)

        val requestBuilder = NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()

        connectivityManager.registerNetworkCallback(requestBuilder , networkCallback)
    }

}