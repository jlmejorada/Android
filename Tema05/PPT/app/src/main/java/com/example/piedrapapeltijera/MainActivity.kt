package com.example.piedrapapeltijera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.piedrapapeltijera.ui.theme.PiedraPapelTijeraTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var coroutine: CoroutineScope
        lateinit var basedatos: JugadorDataBase
        lateinit var todas: List<JugadorEntity>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, JugadorDataBase::class.java, "tareas-db"
        ).build()
        enableEdgeToEdge()
        setContent {
            PiedraPapelTijeraTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pantalla1"
                ) {
                    composable("pantalla1") { PantallaInicio(navController) }
                    composable("pantalla2/{nombre}") { backStackEntry ->
                        PantallaJuego(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }
                    composable("pantalla3") { Puntuaciones(navController) }
                }
            }
        }
    }
}
