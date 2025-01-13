package com.example.kasparoom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kasparoom.ENT.JugadorEntity


@Composable
fun PantallaDetalle(navController: NavHostController, id: Int) {
    var jug by remember {
        mutableStateOf(
            JugadorEntity(
                nombre = "nombre",
                puntos = 0,
                fecha = ""
            )
        )
    }
    // Variables de estadística de partidas
    var jugadas by remember { mutableStateOf(0) }
    var ganadas by remember { mutableStateOf(0) }
    var empatadas by remember { mutableStateOf(0) }
    var perdidas by remember { mutableStateOf(0) }

    LaunchedEffect(id) {
        jug = MainActivity.basedatos.kaspaDao().detallesJugador(id)


        ganadas = MainActivity.basedatos.kaspaDao().detallesGanadasLocal(id) + MainActivity.basedatos.kaspaDao().detallesGanadasVisitante(id)

        empatadas = MainActivity.basedatos.kaspaDao().detallesEmpatadasLocal(id).toInt() + MainActivity.basedatos.kaspaDao().detallesEmpatadasVisitante(id).toInt()

        perdidas = MainActivity.basedatos.kaspaDao().detallesPerdidasLocal(id).toInt() + MainActivity.basedatos.kaspaDao().detallesPerdidasVisitante(id).toInt()

        jugadas = ganadas + empatadas + perdidas
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Detalles Jugador:",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Nombre: ${jug.nombre}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Puntos: ${jug.puntos}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Partidas jugadas: $jugadas",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Partidas ganadas: $ganadas",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Partidas empatadas: $empatadas",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Partidas perdidas: $perdidas",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.size(width = 150.dp, height = 60.dp),
            onClick = {
                navController.navigate("Pantalla4")
            }
        ) {
            Text("Agregar Partida", textAlign = TextAlign.Center)
        }
    }
}