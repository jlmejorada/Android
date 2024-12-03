package ENT

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Mascotas")
data class MascotaEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre:String = "",
    var tipo:String="",
    var edad:Int = 0,
    var foto:String = "defaultanimal"
)