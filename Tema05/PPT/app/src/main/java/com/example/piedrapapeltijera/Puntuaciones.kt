package com.example.piedrapapeltijera

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.piedrapapeltijera.MainActivity.Companion.basedatos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Puntuaciones(navController: NavHostController) {
    var jugadores by remember { mutableStateOf<List<JugadorEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        jugadores = cargaJugadores()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())

    ) {
        JugadoresList(jugadores = jugadores)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {navController.navigate("pantalla1")}) {
                Text("Atras")
            }
        }
    }
    Log.d(TAG, jugadores.toString())
}

@Composable
fun JugadoresList(jugadores: List<JugadorEntity>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(jugadores) { jugador ->
            JugadorItem(jugador = jugador)
        }
    }
}

@Composable
fun JugadorItem(jugador: JugadorEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Nombre: ${jugador.nombre}")
        Text(text = "Jugadas: ${jugador.partidasJugadas}")
        Text(text = "Ganadas: ${jugador.partidasGanadas}")
        Text(text = "Rondas Ganadas: ${jugador.luchasGanadas}")
    }
}

suspend fun cargaJugadores(): List<JugadorEntity> {
    return withContext(Dispatchers.IO) {
        basedatos.JugadorDao().getAll()
    }
}