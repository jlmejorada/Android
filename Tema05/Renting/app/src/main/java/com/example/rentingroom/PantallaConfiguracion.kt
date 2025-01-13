package com.example.rentingroom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rentingroom.ENT.ConfiguracionEntity
import kotlinx.coroutines.launch

@Composable
fun PantallaConfiguracion(navController: NavHostController){
    var numHabitaciones by remember { mutableStateOf("0") }
    var precioHabitaciones by remember { mutableStateOf("0") }
    val coroutineScope = rememberCoroutineScope()
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
            text = "Configuración del cine:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(60.dp)
        )
        OutlinedTextField(
            value = numHabitaciones,
            onValueChange = { numHabitaciones = it },
            label = { Text("Numero de habitaciones:") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Ej. 8") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = precioHabitaciones,
            onValueChange = { precioHabitaciones = it },
            label = { Text("Precio habitaciones") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Ej. 3") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(50.dp)
        )
        Button(

            modifier = Modifier
                .size(width = 150.dp, height = 60.dp),
            onClick = {
                coroutineScope.launch {
                    guardaConfiguracion(numHabitaciones, precioHabitaciones, navController)
                }
            }
        ) {
            Text("Guardar Configuración", textAlign = TextAlign.Center)
        }
    }
}

suspend fun guardaConfiguracion(habitaciones: String, precio: String, navController: NavHostController) {
    var conf : ConfiguracionEntity
    if (habitaciones!="0" && precio!="0"){
        conf = ConfiguracionEntity(
            id = 1,
            numHabitaciones = habitaciones.toInt(),
            precio = precio.toFloat()
        )
        MainActivity.basedatos.rentingDao().insertarConfiguracion(conf)
        navController.navigate("pantalla1")
    }
}
