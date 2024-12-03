package com.example.examen

import ENT.MascotaEntity
import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistrar(navController: NavHostController){
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }
    var estaAbierto by remember { mutableStateOf(false) }
    val listaTipo = listOf("perro", "pajaro", "gato", "huron")
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
            text = "Agregar Mascota:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(60.dp)
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre:") },
            placeholder = { Text("Ej. Woody") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad:") },
            placeholder = { Text("Ej. 4") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
            value = foto,
            onValueChange = { foto = it },
            label = { Text("Foto:") },
            placeholder = { Text("Ej. perropequines") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        ExposedDropdownMenuBox(
            expanded = estaAbierto,
            onExpandedChange = { estaAbierto = !estaAbierto },

            ) {
            OutlinedTextField(
                value = tipo,
                onValueChange = { },
                label = { Text("Selecciona un tipo") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estaAbierto) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = estaAbierto,
                onDismissRequest = { estaAbierto = false }
            ) {
                listaTipo.forEach { listatipito ->
                    DropdownMenuItem(
                        text = { Text(listatipito) },
                        onClick = {
                            tipo = listatipito
                            estaAbierto = false
                        },
                        modifier = Modifier.background(Color(0xFFF2F0EF))
                    )
                }
            }
        }
        Spacer(
            Modifier.height(30.dp)
        )
        Button(

            modifier = Modifier
                .size(width = 150.dp, height = 60.dp),
            onClick = {
                MainActivity.coroutine.launch {
                    guardaMascota(nombre, edad, foto, tipo, navController)
                }
            }
        ) {
            Text("Guardar Mascota", textAlign = TextAlign.Center)
        }
    }
}
suspend fun guardaMascota(nombre: String, edad: String, foto: String, tipo:String, navController: NavHostController) {
    var mas : MascotaEntity
    if (nombre!="" && edad!="0" && tipo!=""){
        mas = MascotaEntity(
            nombre = nombre,
            edad = edad.toInt(),
            foto = foto,
            tipo = tipo
        )
        MainActivity.basedatos.animalDao().insertaAnimal(mas)
        navController.navigate("Pantalla1")

    }
}