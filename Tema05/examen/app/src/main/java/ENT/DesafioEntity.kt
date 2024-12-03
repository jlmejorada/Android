package ENT

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Desafios")
data class DesafioEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 1,
    var nombreDesafio:String = "",
    var fecha:String = "",
    var puntuacion:Int = 0
)