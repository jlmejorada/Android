package com.example.examen

import ENT.DesafioEntity
import ENT.MascotaDesafioEntity
import ENT.MascotaEntity
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
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDesafio(navController: NavHostController){
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var punt by remember { mutableStateOf("") }
    var desafios by remember { mutableStateOf<List<DesafioEntity>>(emptyList()) }
    var estaAbierto by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        desafios=MainActivity.basedatos.animalDao().listaDesafios()
    }
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
            text = "Agregar Desafio:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(60.dp)
        )
        ExposedDropdownMenuBox(
            expanded = estaAbierto,
            onExpandedChange = { estaAbierto = !estaAbierto },

            ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { },
                label = { Text("Selecciona un desafio") },
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
                desafios.forEach { desafio ->
                    DropdownMenuItem(
                        text = { Text(desafio.nombreDesafio) },
                        onClick = {
                            nombre = desafio.nombreDesafio
                            estaAbierto = false
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
            placeholder = { Text("Ej. 22/11/2000") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(20.dp)
        )
        OutlinedTextField(
                value = punt,
                onValueChange = { punt = it },
                label = { Text("Puntuación: ") },
                placeholder = { Text("Ej. 8") },
                singleLine = true
        )
        Spacer(
            Modifier.height(20.dp)
        )
        Button(

            modifier = Modifier
                .size(width = 150.dp, height = 60.dp),
            onClick = {
                MainActivity.coroutine.launch {
//                    guardaDesafio(nombre, fecha, punt, navController)
                }
            }
        ) {
            Text("Guardar Mascota", textAlign = TextAlign.Center)
        }
    }
}
//suspend fun guardaDesafio(nombre: String, fecha: String, punt: String, navController: NavHostController) {
//    var des : MascotaDesafioEntity
//    if (nombre!="" && fecha!="0" && (punt.toInt() in 1..10)){
//        des = DesafioEntity(
//            nombreDesafio = nombre,
//            fecha = fecha,
//            puntuacion = punt.toInt()
//        )
//        MainActivity.basedatos.animalDao().insertarDesafio(des)
//        navController.navigate("Pantalla4")
//
//    }
//}