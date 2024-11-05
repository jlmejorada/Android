package com.example.listatareas.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listatareas.MainActivity
import com.example.listatareas.dal.TareasEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Lista
 */
@Composable
fun lista(listaTareas: List<TareasEntity>, coroutineScope: CoroutineScope) {
    LazyColumn(){
        items(listaTareas){ tarea ->
            filaTarea(tarea, coroutineScope)

        }
    }
}

/**
 * Nueva tarea
 */
@Composable
fun nuevaTarea(coroutineScope: CoroutineScope, listaTareas: SnapshotStateList<TareasEntity>) {
    var texto by remember { mutableStateOf("")}
    Row(){
        TextField(
            value = texto,
            onValueChange = {
                texto = it
            },
            label = {
                Text(text = "Nueva Tarea")
            }
        )
        Button(
            onClick = {
                val tarea = TareasEntity()
                tarea.name = texto
                coroutineScope.launch {
                    MainActivity.basedatos.TareasDao().insertar(tarea)
                    listaTareas.add(tarea)
                    texto=""
                }
            }
        ) {
            Text(text="Añadir")
        }
    }
}

/**
 * Fila tarea
 */
@Composable
fun filaTarea(tarea: TareasEntity, coroutineScope: CoroutineScope) {
    var checked by remember { mutableStateOf(tarea.isDone) }
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                    checked = it
                tarea.isDone = checked
                    coroutineScope.launch {
                        MainActivity.basedatos.TareasDao().actualizar(tarea)

                    }
            }
        )
        Text(
            text=tarea.name
        )
    }
}

/**
 * Vista app
 */
@Composable
fun miApp(){
    val coroutineScope= rememberCoroutineScope()
    var listaTareas = remember { mutableStateListOf<TareasEntity>() }
    LaunchedEffect(Unit) {
        listaTareas.clear()
        listaTareas.addAll(MainActivity.basedatos.TareasDao().getAll())
    }

    Column (
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
        ){
        Spacer(Modifier.height(50.dp))
        nuevaTarea(coroutineScope, listaTareas)
        lista(listaTareas, coroutineScope)
    }


}