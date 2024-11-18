package com.example.contactosbdd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ContactosBDD.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



@Composable
fun PantallaContactos(navController: NavHostController, nombre: String?) {
    var contactos by remember { mutableStateOf<List<ContactoEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        contactos = SacaContactos()
    }
    Column {
        Text(text = "Lista de contactos de $nombre")
        Spacer(modifier = Modifier.height(15.dp))
        ListaContactos(contactos);
    }

}

@Composable
fun ListaContactos(lista: List<ContactoEntity>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(lista) { contacto ->
            CartaDeContacto(contacto)
        }
    }
}

@Composable
fun CartaDeContacto(contacto: ContactoEntity) {
    var imagen = when (contacto.imagen) {
        "corto" -> R.drawable.pelocorto
        "largo" -> R.drawable.pelolargo
        else -> R.drawable.basico
    }
    Card(Modifier.fillMaxWidth()) {
        Row {
            Column {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            Column {
                Text(
                    text = contacto.nombre,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.telefono
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

suspend fun SacaContactos(): List<ContactoEntity>{
    return withContext(Dispatchers.IO){
        MainActivity.basedatos.ContactoDao().getAll();
    }
}