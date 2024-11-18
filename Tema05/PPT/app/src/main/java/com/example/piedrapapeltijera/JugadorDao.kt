package com.example.piedrapapeltijera

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JugadorDao {
    @Query("SELECT * FROM Jugadores WHERE nombre = :nombre LIMIT 1")
    suspend fun getScoreByUsername(nombre: String): JugadorEntity?

    @Query("SELECT * FROM Jugadores")
    suspend fun getAll():List<JugadorEntity>

    @Query("SELECT * FROM Jugadores WHERE nombre = :nombre LIMIT 1")
    suspend fun getJugador(nombre: String): JugadorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(jugador: JugadorEntity): Long

    @Update
    suspend fun actualizar (jugador: JugadorEntity)

    @Query("UPDATE Jugadores SET partidasJugadas = partidasJugadas + 1 WHERE nombre = :nombre")
    suspend fun incrementaPartidasJugadas(nombre: String)

    @Query("UPDATE Jugadores SET luchasGanadas = luchasGanadas + 1 WHERE nombre = :nombre")
    suspend fun incrementaRondasGanadas(nombre: String)

    @Query("UPDATE Jugadores SET partidasGanadas = partidasGanadas + 1 WHERE nombre = :nombre")
    suspend fun incrementaPartidasGanadas(nombre: String)
}