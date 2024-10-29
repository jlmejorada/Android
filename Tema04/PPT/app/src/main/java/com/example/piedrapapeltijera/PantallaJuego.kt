package com.example.piedrapapeltijera

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PantallaJuego(navController: NavHostController) {
    var ctx = LocalContext.current
    var res = ""
    var puntJ1 by remember { mutableIntStateOf(0) }
    var puntPC by remember { mutableIntStateOf(0) }
    var elecJ1 by remember { mutableIntStateOf(0) }
    var elecJPC by remember { mutableIntStateOf(0) }
    val piedra = painterResource(R.drawable.stone)
    val papel = painterResource(R.drawable.paper)
    val tijera = painterResource(R.drawable.shears)
    val incognita = painterResource(R.drawable.que)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "$puntPC VS $puntJ1",
            fontSize = 30.sp
        )
        Spacer(
            Modifier.height(140.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    painter = when (elecJ1) {
                        1 -> piedra
                        2 -> papel
                        3 -> tijera
                        else -> incognita
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Text(text = "J1")
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    painter = when (elecJPC) {
                        1 -> piedra
                        2 -> papel
                        3 -> tijera
                        else -> incognita
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Text(text = "PC")
            }
        }
        Spacer(
            Modifier.height(90.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = piedra,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 1
                        elecJPC = CalculaPc()
                        res = sacaGanador(elecJ1, elecJPC)
                        Toast.makeText(ctx, res, Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = papel,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 2
                        elecJPC = CalculaPc()
                        res = sacaGanador(elecJ1, elecJPC)
                        Toast.makeText(ctx, res, Toast.LENGTH_SHORT).show()
                    }
            )
            Image(
                painter = tijera,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(10.dp)
                    .clickable {
                        elecJ1 = 3
                        elecJPC = CalculaPc()
                        res = sacaGanador(elecJ1, elecJPC)
                        Toast.makeText(ctx, res, Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}

fun sacaGanador(elecJ1: Int, elecJPC: Int): String {

    return "Ganador J1"
}

fun CalculaPc(): Int {
    val random1 = (1..3).shuffled().last()
    return random1
}
