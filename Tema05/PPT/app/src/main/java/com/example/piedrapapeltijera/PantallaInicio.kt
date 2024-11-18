package com.example.piedrapapeltijera

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.piedrapapeltijera.MainActivity.Companion.basedatos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PantallaInicio(navController: NavHostController) {
    val image = painterResource(R.drawable.ppt)
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Image (
            painter = image,
            null
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre= it },
            label = { Text("Jugador") },
            placeholder = { Text("Jugador") },
            singleLine = true,
        )
        Spacer(
            Modifier.height(50.dp)
        )
        Button (
            onClick = {
                coroutineScope.launch {

                    btnLogin(nombre.text.toString(), navController)
                }
            },
            Modifier
                .width(140.dp)
                .height(42.dp)
        ){
            Text("Entrar")
        }
    }


}

suspend fun btnLogin(nombre: String, navController: NavController){
    if(nombre!= "" && nombre != null ){
        LoginJugador(nombre)
        navController.navigate("pantalla2/${nombre}")
    }


}
private suspend fun LoginJugador(nombre: String) {
    withContext(Dispatchers.IO) {
        val jugador = basedatos.JugadorDao().getJugador(nombre)

        if (jugador == null) {
            val nuevoJugador = JugadorEntity(nombre = nombre)
            basedatos.JugadorDao().insertar(nuevoJugador)
            Log.d(TAG, nuevoJugador.nombre)
        }
    }
}
