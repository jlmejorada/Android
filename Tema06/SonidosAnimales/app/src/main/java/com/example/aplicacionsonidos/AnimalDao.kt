package DAL

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aplicacionsonidos.AnimalEntity

@Dao
interface AnimalDao {

    @Query("SELECT * FROM Animal")
    suspend fun sacaAnimales():List<AnimalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAnimal(animal: AnimalEntity)

}