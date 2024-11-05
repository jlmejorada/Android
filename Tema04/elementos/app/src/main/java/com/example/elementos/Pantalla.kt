package com.example.elementos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import android.net.ConnectivityManager
import android.location.LocationManager
import android.bluetooth.BluetoothManager
import android.net.wifi.WifiManager
import android.nfc.NfcManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment

@Composable
fun Pantalla () {

    val context: Context = LocalContext.current.applicationContext
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val gpsManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val isWifiEnabled = wifiManager.isWifiEnabled
    val bluetoothAdapter = bluetoothManager.adapter
    val nfcAdapter = nfcManager.defaultAdapter
    val isGpsEnabled = gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val activeNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    val isMobileDataActive = networkCapabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) == true

    Column(
    modifier = Modifier
    .fillMaxSize(),


    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly

    ){
        Text(
            text = "Wifi",
            modifier = Modifier
                .background(
                    if (isWifiEnabled) {
                        Color.Green
                    } else {
                        Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Text(
            text = "Datos móviles",
            modifier = Modifier
                .background(
                    if (isMobileDataActive){
                        Color.Green
                    } else {
                        Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Text(
            text = "Bluetooth",
            modifier = Modifier
                .background(
                    if (bluetoothAdapter?.isEnabled == true){
                        Color.Green
                    } else{
                        Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Text(
            text = "NFC",
            modifier = Modifier
                .background(
                    if (nfcAdapter?.isEnabled == true){
                        Color.Green
                    } else{
                        Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Text(
            text = "GPS: ${isGpsEnabled}",
            modifier = Modifier
                .background(
                    if (isGpsEnabled){
                        Color.Green
                    } else {
                        Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )


    }

}