package id.novian.rickandmortycharacterlocations.data.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location): Long

    @Update
    suspend fun updateCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("""
        SELECT * FROM Character
    """)
    suspend fun getAllCharacter(): List<Character>

    @Query("SELECT * FROM location")
    suspend fun getAllLocations(): List<Location>

    @Query("SELECT * FROM character WHERE location_id = :locationId")
    suspend fun getCharactersByLocationId(locationId: Int): List<Character>

    @Query("SELECT * FROM location WHERE id = :locationId")
    suspend fun getLocationById(locationId: Int): Location?

    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): Character?

    @Query("SELECT * FROM location where name = :name")
    suspend fun getLocationsByName(name: String): Location?

    @Transaction
    suspend fun assignCharacterToLocation(characterId: Int, locationId: Int? = null, locationName: String? = null) {
        val character = getCharacterById(characterId)

        if (locationId != null) {
            val location = getLocationById(locationId)
            if (character != null && location != null) {
                character.locationId = locationId
                updateCharacter(character)
            }
        }

        if (locationName != null) {
            val newLocation = Location(name = locationName)
            insertLocation(newLocation)

            val location = getLocationsByName(locationName)!!

            if (character != null) {
                character.locationId = location.id
                updateCharacter(character)
            }
        }
    }

    @Transaction
    suspend fun unassignCharacterFromLocation(characterId: Int) {
        val character = getCharacterById(characterId)
        if (character != null) {
            character.locationId = null
            updateCharacter(character)
        }
    }

    @Query("SELECT * FROM character WHERE location_id = :locationId")
    suspend fun getCharactersByLocations(locationId: Int): List<CharacterWithLocation>
}