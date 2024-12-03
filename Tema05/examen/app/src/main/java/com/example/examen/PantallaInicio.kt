package com.example.examen

import ENT.MascotaEntity
import ENT.UsuarioEntity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PantallaInicio(navController: NavHostController){
    var usu by remember { mutableStateOf(UsuarioEntity(id = 0, nombreUsuario = "", avatar = "")) }
    var animales by remember { mutableStateOf<List<MascotaEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        clientePorDefecto()
        usu = MainActivity.basedatos.animalDao().sacaUsuario()
        animales = MainActivity.basedatos.animalDao().sacaAnimales()
    }
    Column(
        modifier = Modifier
            .height(60.dp)
    ){
        CartaDeUsuario(usu)
        ListaAnimaless(animales, navController)
    }
}


@Composable
fun CartaDeUsuario(usu: UsuarioEntity){
    var imagen = when (usu.avatar) {
        "callabo" -> R.drawable.callabo
        else -> R.drawable.defaultusu
    }
    Row {
        Image(
            painter = painterResource(imagen),
            contentDescription = "Foto contacto",
            Modifier.height(70.dp)
        )
        Text(
            text = usu.nombreUsuario,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ListaAnimaless(lista: List<MascotaEntity>, navController: NavHostController) {
    LazyColumn {
        items(lista) { mascota ->
            Column (
                Modifier.padding(10.dp)
            ){
                CartaDeMascota(mascota, navController)
            }
        }
    }
}

@Composable
fun CartaDeMascota(animal: MascotaEntity, navController: NavHostController) {
    var imagen = when (animal.foto) {
        else -> R.drawable.defaultanimal
    }
    Card(
        Modifier.fillMaxWidth()
            .clickable {
                navController.navigate("pantalla3/${animal.id}")
        }
    ) {
        Row {
            Image(
                painter = painterResource(imagen),
                contentDescription = "Foto animal",
                Modifier.height(50.dp)
            )
            Text(
                text = animal.nombre,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

suspend fun clientePorDefecto() {
    val usu = UsuarioEntity(
            nombreUsuario = "JLMejorada",
            avatar = "callabo"
    )
    MainActivity.basedatos.animalDao().insertarUsuario(usu)
}