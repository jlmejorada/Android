package com.example.piedrapapeltijeras

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JugadorDao {
    @Query("SELECT * FROM Jugadores")
    suspend fun getAll():List<JugadorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(tarea: JugadorEntity): Long

    @Update
    suspend fun actualizar (tarea: JugadorEntity)
}