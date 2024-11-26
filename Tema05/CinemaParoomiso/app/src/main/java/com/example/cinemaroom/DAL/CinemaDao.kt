package com.example.cinemaroom.DAL

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cinemaroom.ENT.ClienteEntity
import com.example.cinemaroom.ENT.ConfiguracionEntity

@Dao
interface CinemaDao {

    //INSERTS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarConfiguracion(configuracion: ConfiguracionEntity)

    //UPDATES
    @Update
    suspend fun actualizarConfiguracion (configuracion: ConfiguracionEntity)

    //CONSULTA
    @Query("SELECT * FROM Configuracion Where Id = 1")
    suspend fun sacaConfiguracion():ConfiguracionEntity

//
//    @Query("SELECT * FROM Clientes")
//    suspend fun getAll():List<ContactoEntity>
//
//    @Query("SELECT * FROM Contactos WHERE nombre = :nombre LIMIT 1")
//    suspend fun getContactoNom(nombre: String): ContactoEntity?
//
//    @Query("SELECT * FROM Contactos WHERE id = :id LIMIT 1")
//    suspend fun getContactoID(id: Int): ContactoEntity?
//
//
//    @Update
//    suspend fun actualizar (contacto: ContactoEntity)

}