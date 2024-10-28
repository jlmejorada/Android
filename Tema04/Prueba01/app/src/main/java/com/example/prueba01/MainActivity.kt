package com.example.prueba01

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.nfc.NfcManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.prueba01.ui.theme.Prueba01Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba01Theme {
                conexiones()
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun conexiones(){
    val context: Context = LocalContext.current.applicationContext
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val gpsManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nfcAdapter = nfcManager.defaultAdapter
    val bluetoothAdapter = bluetoothManager.adapter
    val isGpsEnabled = gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    var colorWifi= Color.Red
    var colorBluetooth = Color.Red
    var colorNFC=Color.Red
    var colorGPS=Color.Red
    var colorInternet=Color.Red
    if (wifiManager.isWifiEnabled)
        colorWifi=Color.Green
    if (bluetoothAdapter.isEnabled)
        colorBluetooth=Color.Green
    if (nfcAdapter!=null && nfcAdapter.isEnabled)
        colorNFC=Color.Green
    if (isGpsEnabled)
        colorGPS=Color.Green
    if (connectivityManager.isActiveNetworkMetered)
        colorInternet=Color.Green

    Column (
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text="WiFi")
        Text(text = "Datos Moviles")
        Text(text = "Blutetooth")
        Text(text = "NFC")
        Text(text = "GPS")
    }
}