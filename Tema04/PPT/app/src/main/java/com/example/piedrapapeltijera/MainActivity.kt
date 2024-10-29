package com.example.piedrapapeltijera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piedrapapeltijera.ui.theme.PiedraPapelTijeraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiedraPapelTijeraTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pantalla1"
                ) {
                    composable("pantalla1") { PantallaInicio(navController) }
                }
            }
        }
    }
}
