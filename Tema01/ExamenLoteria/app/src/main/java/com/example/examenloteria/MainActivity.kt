package com.example.examenloteria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "pantalla1"
            ) {
                composable("pantalla1") { PantallaEleccion(navController) }
                composable("pantalla2/{num}") { backStackEntry ->
                    PantallaApuesta(
                        navController,
                        modifier = Modifier,
                        num = backStackEntry.arguments?.getString("num"),
                    )
                }
                composable("pantalla3") { PantallaResultado(navController) }
            }
        }
    }

}