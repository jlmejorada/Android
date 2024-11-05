package com.example.listatareas.dal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")data class TareasEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0, // Id de la tarea
    var name:String = "", // Nombre de la tarea
    var isDone:Boolean = false // Booleano que indica si la tarea está hecha o no

)
