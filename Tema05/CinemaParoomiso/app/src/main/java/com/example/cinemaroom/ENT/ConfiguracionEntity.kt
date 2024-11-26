package com.example.cinemaroom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuracion")
data class ConfiguracionEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var numSalas:Int = 0,
    var numAsientos:Int = 0,
    var precioPalomitas:Float = 0.0f
)