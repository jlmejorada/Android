package com.example.examen

import ENT.DesafioEntity
import ENT.MascotaDesafioEntity
import ENT.MascotaEntity
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun PantallaDetalle(navController: NavHostController, idMascota: Int){
    var mas by remember {
        mutableStateOf(
            MascotaEntity(
                    nombre = "nombre",
                    edad = 0,
                    foto = "",
                    tipo = ""
                )
        )
    }
    var masDesafios by remember { mutableStateOf<List<MascotaDesafioEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        mas = MainActivity.basedatos.animalDao().detallesMascota(idMascota)
        masDesafios=MainActivity.basedatos.animalDao().desafiosMascota(mas.id)
    }
    val imagen = when (mas.foto) {
        else -> R.drawable.defaultanimal
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Detalles Mascota:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(60.dp)
        )
        Image(
        painter = painterResource(imagen),
        contentDescription = "Foto animal",
        Modifier.height(100.dp)
    )
        Text(
            text = "Nombre: ${mas.nombre}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Edad: ${mas.edad}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Tipo mascota: ${mas.tipo}",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Spacer(
            Modifier.height(10.dp)
        )
        ListaDesafios(masDesafios)
        Spacer(
            Modifier.height(20.dp)
        )
        Button(

            modifier = Modifier
                .size(width = 150.dp, height = 60.dp),
            onClick = {
                navController.navigate("Pantalla4")
            }
        ) {
            Text("Agregar Desafio", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ListaDesafios(lista: List<MascotaDesafioEntity>) {
    LazyColumn {
        items(lista) { desafio ->
            Column (
                Modifier.padding(10.dp)
            ){
                CartaDeDesafio(desafio)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartaDeDesafio(masDesafio: MascotaDesafioEntity) {
    var desafio by remember {
        mutableStateOf(
            DesafioEntity(
                nombreDesafio = "",
                fecha = "",
                puntuacion = 0
            )
        )
    }
    MainActivity.coroutine.launch {
        desafio = MainActivity.basedatos.animalDao().sacaDesafio(masDesafio.idDesafio)
    }
    Card(
        Modifier.fillMaxWidth()
    ) {
        Row {
            Text(
                text = desafio.nombreDesafio,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = desafio.puntuacion.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}