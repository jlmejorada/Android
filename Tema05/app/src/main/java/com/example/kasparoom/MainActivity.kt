package com.example.kasparoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kasparoom.DAL.KaspaDatabase
import com.example.kasparoom.ui.theme.KaspaRoomTheme
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {
    companion object {
        lateinit var basedatos: KaspaDatabase
        lateinit var coroutine: CoroutineScope
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            basedatos = Room.databaseBuilder(
                this, KaspaDatabase::class.java, "kaspa-db"
            ).build()

            coroutine = rememberCoroutineScope()

            KaspaRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 10.dp),
                        navController = navController,
                        startDestination = "pantalla1",

                        ) {
                        composable("pantalla1") { PantallaInicio(navController) }
                        composable("pantalla2") { PantallaAgregarJugador(navController) }
                        composable("pantalla3/{jugadorId}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("jugadorId") ?: "0"
                            PantallaDetalle(
                                navController,
                                id = id.toInt(),
                            )
                        }
                        composable("pantalla4") { PantallaAgregarPartida(navController) }
                    }
                }
            }
        }
    }
}

