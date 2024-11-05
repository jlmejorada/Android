package com.example.prueba

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class TareasEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var name : String = "",
    var isDone: Boolean = false
)
