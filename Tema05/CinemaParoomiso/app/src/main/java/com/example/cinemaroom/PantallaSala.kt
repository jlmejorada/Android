package com.example.cinemaroom

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cinemaroom.ENT.ClienteEntity
import com.example.cinemaroom.ENT.ConfiguracionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PantallaSala(navController: NavHostController) {
    var conf by remember { mutableStateOf(ConfiguracionEntity(id = 0, numSalas = 0, numAsientos = 0, precioPalomitas = 0f)) }
    var coroutine : CoroutineScope = rememberCoroutineScope()
    var enabled by remember { mutableStateOf(true) }
    LaunchedEffect(enabled) {
        var numClientes = 0
        if (enabled) {
            while (numClientes < 100) {
                delay(1000)
                numClientes += entraCliente(conf.numSalas)

            }
        } else {
            enabled = false
        }
    }
    LaunchedEffect(Unit) {
        conf = extraeConfi()
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(Modifier.height(30.dp))
        Text(
            text = "Lista de Salas:",
            fontSize = 30.sp
        )
        LazyColumn (
            modifier = Modifier.padding(10.dp)
        ){
            items(conf.numSalas) { index ->
                CartaDeSalas(index = index + 1, coroutine, conf)
            }
        }
    }
}

// Función suspendida para extraer la configuración
suspend fun extraeConfi(): ConfiguracionEntity {
    return MainActivity.basedatos.cinemaDao().sacaConfiguracion()
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartaDeSalas(index: Int, coroutineScope: CoroutineScope, conf:ConfiguracionEntity) {
    var color : Color by remember { mutableStateOf(Color.White) }
    coroutineScope.launch {
        color = colorCorrespondiente(index, conf)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(16.dp)
    ) {
        Text(
            text = "Sala $index",
            color = Color.White
        )
    }
    Spacer(
        modifier = Modifier.height(10.dp)
    )
}


suspend fun colorCorrespondiente(numSala:Int, conf: ConfiguracionEntity): Color{
    var clientes=MainActivity.basedatos.cinemaDao().cuantosClientesEnSala(numSala)
    var color = Color.Blue
    if (clientes==0) {
        clientes=1
    }
    val porCiento=(clientes*100)/conf.numAsientos
    color = if (porCiento<50){
        Color.Green
    } else if (porCiento<90){
        Color.Yellow
    } else {
        Color.Red
    }
    return color
}

suspend fun entraCliente(numSalas : Int) : Int{

    var numClientes = Random.nextInt(1, 3)
    var salaElegida : Int
    var Cliente : ClienteEntity

    for (i in 1..numClientes) {
        salaElegida = Random.nextInt(1, numSalas+1)
        Cliente = ClienteEntity(salaElegida = salaElegida, palomitas = 0)
        MainActivity.basedatos.cinemaDao().anadeCliente(Cliente)
    }

    return numClientes;
}