package com.example.rentingroom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuracion")
data class ConfiguracionEntity(
    @PrimaryKey(autoGenerate = false)
    var id:Int = 1,
    var numHabitaciones:Int = 0,
    var precio: Float = 0f
)

