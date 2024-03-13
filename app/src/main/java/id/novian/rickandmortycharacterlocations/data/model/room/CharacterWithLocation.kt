package id.novian.rickandmortycharacterlocations.data.model.room

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithLocation(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "location_id",
        entityColumn = "id"
    )
    val location: Location
)
