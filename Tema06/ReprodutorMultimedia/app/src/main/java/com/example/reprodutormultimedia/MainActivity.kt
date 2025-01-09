package com.example.reprodutormultimedia
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 10.dp),
                        navController = navController,
                        startDestination = "pantalla1",

                        ) {
                        composable("pantalla1") { PantallaInicio(navController) }
                        composable("pantalla2") { Video(enlace,navController) }
                        composable("pantalla3/{animalId}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("animalId") ?:"0"
                            PantallaDetalle(navController,
                                idMascota = id.toInt(),
                            )
                        }

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                        contentAlignment = Alignment.BottomCenter
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
                                Text("Ranking")
                            }

                            OutlinedButton(
                                shape = RectangleShape,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                onClick = {
                                    navController.navigate("pantalla1")
                                }
                            ) {
                                Text("Inicio")
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
                                Text("Registrar Mascota")
                            }
                        }
                    }
                }
            }
        }
    }
}


