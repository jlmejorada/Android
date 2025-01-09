package DAL

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplicacionsonidos.AnimalEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [AnimalEntity::class], version = 1)
abstract class AnimalDatabase: RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        lateinit var coroutine: CoroutineScope
    }

}
