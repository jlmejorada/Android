package com.example.contactosbdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.contactosbdd.ui.theme.ContactosBddTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var basedatos: ContactoDataBase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, ContactoDataBase::class.java, "tareas-db"
        ).build()
        enableEdgeToEdge()
        setContent {
            ContactosBddTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pantalla1"
                ) {
                    composable("pantalla1") { PantallaInicio(navController) }
                    composable("pantalla2/{nombre}") { backStackEntry ->
                        PantallaContactos(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }
                }
            }
        }
    }
}