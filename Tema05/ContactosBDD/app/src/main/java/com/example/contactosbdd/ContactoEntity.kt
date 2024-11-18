package com.example.contactosbdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contactos")
data class ContactoEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nombre:String = "",
    var telefono:Int = 0,
    var imagen:String ="oasdkmas"
)