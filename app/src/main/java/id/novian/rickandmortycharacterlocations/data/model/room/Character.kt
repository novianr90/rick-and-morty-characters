package id.novian.rickandmortycharacterlocations.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["location_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val gender: String,
    val species: String,
    val type: String,
    val url: String,
    val episode: String,
    val locationName: String,
    val locationUrl: String,
    val originName: String,
    val originUrl: String,
    val created: String,
    @ColumnInfo(name = "location_id") var locationId: Int?
)