package com.example.piedrapapeltijeras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.piedrapapeltijeras.ui.theme.PiedraPapelTijerasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
            coroutine = rememberCoroutineScope()
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