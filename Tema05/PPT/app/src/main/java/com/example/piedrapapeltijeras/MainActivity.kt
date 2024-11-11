package com.example.piedrapapeltijeras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piedrapapeltijeras.ui.theme.PiedraPapelTijerasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiedraPapelTijerasTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pantalla1"
                ) {
                    composable("pantalla1") { PantallaInicio(navController) }
                    composable("pantalla2") { PantallaJuego(navController) }
                }
            }
        }
    }
}