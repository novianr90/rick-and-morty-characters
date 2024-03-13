package id.novian.rickandmortycharacterlocations.data.model.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)