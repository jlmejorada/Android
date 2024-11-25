package com.example.firebaselogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.ui.theme.PantallaSecundaria


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
                    composable("pantalla1") { PantallaInicio(navController) }
                    composable("pantalla2") { PantallaSecundaria(navController) }
//                  composable("pantalla2/{nombre}") { backStackEntry ->
//                        PantallaContactos(
//                            navController,
//                            backStackEntry.arguments?.getString("nombre")
//                        )
//                   }
                }
        }
    }
}
