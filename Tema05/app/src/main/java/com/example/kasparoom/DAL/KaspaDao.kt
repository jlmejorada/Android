package com.example.kasparoom.DAL

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kasparoom.ENT.JugadorEntity
import com.example.kasparoom.ENT.PartidaEntity

@Dao
interface KaspaDao {

    @Query("SELECT * FROM Jugadores ORDER BY puntos DESC")
    suspend fun sacaJugadoresOrden(): List<JugadorEntity>

    @Query("SELECT * FROM Jugadores")
    suspend fun sacaJugadores(): List<JugadorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertaJugador(jugador: JugadorEntity)

    @Query("SELECT * FROM Jugadores WHERE id == :idJugador")
    suspend fun detallesJugador(idJugador: Int): JugadorEntity

    @Query("SELECT COUNT(idJugador1) FROM Partidas WHERE idJugador1 == :idJugador AND resultado = 1")
    suspend fun detallesGanadasLocal(idJugador: Int): Int

    @Query("SELECT COUNT(idJugador2) FROM Partidas WHERE idJugador2 == :idJugador AND resultado = 2")
    suspend fun detallesGanadasVisitante(idJugador: Int): Int

    @Query("SELECT COUNT(idJugador1) FROM Partidas WHERE idJugador1 == :idJugador AND resultado = 0")
    suspend fun detallesEmpatadasLocal(idJugador: Int): Int

    @Query("SELECT COUNT(idJugador2) FROM Partidas WHERE idJugador2 == :idJugador AND resultado = 0")
    suspend fun detallesEmpatadasVisitante(idJugador: Int): Int

    @Query("SELECT COUNT(idJugador1) FROM Partidas WHERE idJugador1 == :idJugador AND resultado = 2")
    suspend fun detallesPerdidasLocal(idJugador: Int): Int

    @Query("SELECT COUNT(idJugador2) FROM Partidas WHERE idJugador2 == :idJugador AND resultado = 1")
    suspend fun detallesPerdidasVisitante(idJugador: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertaPartida(partida: PartidaEntity)
}