package com.example.examen

import DAL.AnimalDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.examen.ui.theme.ExamenTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var basedatos: AnimalDatabase
        lateinit var coroutine: CoroutineScope
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            basedatos = Room.databaseBuilder(
                this, AnimalDatabase::class.java, "animal-db"
            ).build()

            coroutine = rememberCoroutineScope()

            ExamenTheme {
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
                        composable("pantalla2") { PantallaRegistrar(navController) }
                        composable("pantalla3/{animalId}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("animalId") ?:"0"
                            PantallaDetalle(navController,
                                idMascota = id.toInt(),
                            )
                        }
                        composable("pantalla4") { PantallaDesafio(navController) }
                        composable("pantalla5") { PantallaRanking(navController) }

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
                                    navController.navigate("pantalla5")
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
                                    navController.navigate("pantalla2")
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
