package com.example.aplicacionsonidos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrincipalView(){
    var animales by remember { mutableStateOf<List<AnimalEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        AnimalPorDefecto()
        animales = MainActivity.basedatos.animalDao().sacaAnimales()
    }
    Column {
        Text(
            text = "Agregar Mascota:",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(30.dp)
        )
        LazyRow {
            items(animales){ animal ->
                Column (
                    Modifier.padding(10.dp)
                ){
                    CartaAnimal(animal)
                }
            }
        }
    }

}

@Composable
fun CartaAnimal(animal: AnimalEntity) {
    var imagen = when (animal.nombre) {
        "caballo" -> R.drawable.caballo
        else -> R.drawable.defaulta
    }
    Card(
        Modifier.fillMaxWidth()
            .clickable {

            }
    ) {
        Image(
            painter = painterResource(imagen),
            contentDescription = "Foto animal",
            Modifier.height(200.dp)
        )
    }
}

suspend fun AnimalPorDefecto() {
    val animal = AnimalEntity(
        id = 0,
        nombre = "caballo"
    )
    MainActivity.basedatos.animalDao().insertarAnimal(animal)
}