package com.example.contactosbdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactoDao {

    @Query("SELECT * FROM Contactos")
    suspend fun getAll():List<ContactoEntity>

    @Query("SELECT * FROM Contactos WHERE nombre = :nombre LIMIT 1")
    suspend fun getContactoNom(nombre: String): ContactoEntity?

    @Query("SELECT * FROM Contactos WHERE id = :id LIMIT 1")
    suspend fun getContactoID(id: Int): ContactoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(contacto: ContactoEntity): Long

    @Update
    suspend fun actualizar (contacto: ContactoEntity)

}