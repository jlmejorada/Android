package com.example.kasparoom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kasparoom.ENT.JugadorEntity
import com.example.kasparoom.ENT.PartidaEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarPartida(navController: NavHostController) {
    var estaAbiertoJugador1 by remember { mutableStateOf(false) }
    var estaAbiertoJugador2 by remember { mutableStateOf(false) }
    var estaAbiertoResultado by remember { mutableStateOf(false) }
    var jugador1 by remember { mutableStateOf(0) }
    var jugador2 by remember { mutableStateOf(0) }
    var puntuacion by remember { mutableStateOf(0) }
    var fecha by remember { mutableStateOf("") }
    var jugadores by remember { mutableStateOf<List<JugadorEntity>>(emptyList()) }
    val listaResultado = listOf("Ganada", "Empatada", "Perdida")

    LaunchedEffect(Unit) {
        jugadores = MainActivity.basedatos.kaspaDao().sacaJugadores()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(Modifier.height(30.dp))
        Text(text = "Agregar Partida:", fontSize = 30.sp)
        Spacer(Modifier.height(60.dp))

        ExposedDropdownMenuBox(
            expanded = estaAbiertoJugador1,
            onExpandedChange = { estaAbiertoJugador1 = !estaAbiertoJugador1 },
        ) {
            OutlinedTextField(
                value = jugadores.find { it.id.toInt() == jugador1 }?.nombre ?: "",
                onValueChange = { },
                label = { Text("Jugador 1") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estaAbiertoJugador1) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = estaAbiertoJugador1,
                onDismissRequest = { estaAbiertoJugador1 = false }
            ) {
                jugadores.forEach { jugador ->
                    DropdownMenuItem(
                        text = { Text(jugador.nombre) },
                        onClick = {
                            jugador1 = jugador.id.toInt()
                            estaAbiertoJugador1 = false
                        },
                        modifier = Modifier.background(Color(0xFFF2F0EF))
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))

        ExposedDropdownMenuBox(
            expanded = estaAbiertoJugador2,
            onExpandedChange = { estaAbiertoJugador2 = !estaAbiertoJugador2 },
        ) {
            OutlinedTextField(
                value = jugadores.find { it.id.toInt() == jugador2 }?.nombre ?: "",
                onValueChange = { },
                label = { Text("Jugador 2") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estaAbiertoJugador2) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = estaAbiertoJugador2,
                onDismissRequest = { estaAbiertoJugador2 = false }
            ) {
                jugadores.forEach { jugador ->
                    DropdownMenuItem(
                        text = { Text(jugador.nombre) },
                        onClick = {
                            if (jugador.id.toInt() != jugador1) { // Verificación para evitar duplicados
                                jugador2 = jugador.id.toInt()
                                estaAbiertoJugador2 = false
                            } else {
                                // Mostrar un mensaje de error si intentan seleccionar el mismo jugador
                                println("No puedes seleccionar el mismo jugador para ambos campos.")
                            }
                        },
                        modifier = Modifier.background(Color(0xFFF2F0EF))
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))

        // Resultado Dropdown
        ExposedDropdownMenuBox(
            expanded = estaAbiertoResultado,
            onExpandedChange = { estaAbiertoResultado = !estaAbiertoResultado },
        ) {
            OutlinedTextField(
                value = puntuacion.toString(),
                onValueChange = { },
                label = { Text("Resultado") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estaAbiertoResultado) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = estaAbiertoResultado,
                onDismissRequest = { estaAbiertoResultado = false }
            ) {
                listaResultado.forEach { resultado ->
                    DropdownMenuItem(
                        text = { Text(resultado) },
                        onClick = {
                            puntuacion = when (resultado) {
                                "Ganada" -> 1
                                "Perdida" -> 2
                                else -> 0
                            }
                            estaAbiertoResultado = false
                        },
                        modifier = Modifier.background(Color(0xFFF2F0EF))
                    )
                }
            }
        }
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha:") },
            placeholder = { Text("Ej. 2005-11-22") },
            singleLine = true,
        )
        Spacer(Modifier.height(30.dp))

        Button(
            modifier = Modifier.size(width = 150.dp, height = 60.dp),
            onClick = {
                MainActivity.coroutine.launch {
                    guardaPartida(jugador1, jugador2, puntuacion, fecha, navController)
                }
            }
        ) {
            Text("Guardar Partida", textAlign = TextAlign.Center)
        }
    }
}

suspend fun guardaPartida(jugador1: Int, jugador2: Int, puntuacion: Int, fecha:String, navController: NavHostController) {
    var part : PartidaEntity
    if (jugador1!=0 && jugador2!=0 && puntuacion!=0 && fecha!=""){
        part = PartidaEntity(
            idJugador1 = jugador1.toLong(),
            idJugador2 = jugador2.toLong(),
            resultado = puntuacion,
            fecha = fecha
        )
        MainActivity.basedatos.kaspaDao().insertaPartida(part)
        navController.navigate("Pantalla1")

    }
}
