package ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MascotaDesafios")
data class MascotaDesafioEntity (
    @PrimaryKey()
    var idMascota:Long = 0,
    var idDesafio:Long = 0,
    var puntuacion:Int = 0
)