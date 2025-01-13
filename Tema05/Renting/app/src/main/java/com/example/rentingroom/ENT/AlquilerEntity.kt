package com.example.rentingroom.ENT

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp


@Entity(tableName = "Alquileres")
data class AlquilerEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var fechaEntrada:Long,
    var fechaSalida:Long,
    var idCliente:Int = 0
)
