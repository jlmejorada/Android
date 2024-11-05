package com.example.prueba

import androidx.room.Dao
import androidx.room.Query


@Dao
interface TareaDao {
    @Query("SELECT * FROM tareas")
    fun getAll():List<TareasEntity>
}