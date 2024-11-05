package com.example.listatareas.dal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TareasDao {
    @Query("SELECT * FROM tareas")
    suspend fun getAll():List<TareasEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(tarea: TareasEntity): Long

    @Update
    suspend fun actualizar (tarea: TareasEntity)
}