package com.example.kasparoom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kasparoom.ENT.JugadorEntity
import kotlinx.coroutines.launch

@Composable
fun PantallaAgregarJugador(navController: NavHostController){
    var nombre by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(
            Modifier.height(30.dp)
        )
        Text(
            text = "Agregar Jugador:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(60.dp)
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre:") },
            placeholder = { Text("Ej. Juan") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = puntos,
            onValueChange = { puntos = it },
            label = { Text("Puntos:") },
            placeholder = { Text("Ej. 72") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fechas:") },
            placeholder = { Text("Ej. 72") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        Button(

            modifier = Modifier
                .size(width = 150.dp, height = 60.dp),
            onClick = {
                MainActivity.coroutine.launch {
                    guardaJugador(nombre, puntos.toInt(), fecha, navController)
                }
            }
        ) {
            Text("Guardar Jugador", textAlign = TextAlign.Center)
        }
    }
}
suspend fun guardaJugador(nombre: String, puntos: Int, fecha: String, navController: NavHostController) {
    var jug : JugadorEntity
    if (nombre!="" && puntos!=0 && fecha!=""){
        jug = JugadorEntity(
            nombre = nombre,
            puntos = puntos,
            fecha = fecha,
        )
        MainActivity.basedatos.kaspaDao().insertaJugador(jug)
        navController.navigate("Pantalla1")

    }
}