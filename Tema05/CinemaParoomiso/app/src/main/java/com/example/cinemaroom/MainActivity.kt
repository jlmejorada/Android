package com.example.cinemaroom

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.cinemaroom.DAL.CinemaDataBase
import com.example.cinemaroom.ui.theme.CinemaRoomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var basedatos: CinemaDataBase
        lateinit var coroutine: CoroutineScope
    }
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val navController = rememberNavController()


            basedatos = Room.databaseBuilder(
                this, CinemaDataBase::class.java, "cine-db"
            ).build()


            coroutine = rememberCoroutineScope()


            CinemaRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 60.dp),
                        navController = navController,
                        startDestination = "pantalla1",

                        ) {
                        composable("pantalla1") { PantallaConfiguracion(navController) }
                        composable("pantalla2") { PantallaSala(navController) }
                        composable("pantalla3") { PantallaLista(navController) }
                        composable("pantalla4/{idSala}") { backStackEntry ->
                            var id = backStackEntry.arguments?.getString("idSala") ?:"0"
                            Log.d("IDFEO", "onCreate: $id")
                            PantallaDetalles(
                                idSala = id.toInt()
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding()),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Row(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                onClick = {
                                    navController.navigate("pantalla1")
                                }
                            ) {
                                Text("Configuración")
                            }

                            OutlinedButton(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                onClick = {
                                    navController.navigate("pantalla2")
                                }
                            ) {
                                Text("Pantalla 2")
                            }
                            OutlinedButton(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                onClick = {
                                    navController.navigate("pantalla3")
                                }
                            ) {
                                Text("Pantalla 3")
                            }
                        }
                    }
                }

            }
        }
    }
}
