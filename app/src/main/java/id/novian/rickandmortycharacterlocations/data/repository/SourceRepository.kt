package id.novian.rickandmortycharacterlocations.data.repository

import android.util.Log
import com.google.gson.Gson
import id.novian.rickandmortycharacterlocations.data.model.network.Character
import id.novian.rickandmortycharacterlocations.data.model.room.AppDao
import id.novian.rickandmortycharacterlocations.data.model.room.CharacterWithLocation
import id.novian.rickandmortycharacterlocations.data.model.room.Location
import id.novian.rickandmortycharacterlocations.data.source.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SourceRepository {

    // fetch and save to local
    suspend fun fetchCharacters(): List<Character>

    // Get by network for now
    suspend fun getCharacterById(id: Int): id.novian.rickandmortycharacterlocations.data.model.room.Character?

    // get all characters via local
    suspend fun getCharacters(): List<id.novian.rickandmortycharacterlocations.data.model.room.Character>

    suspend fun assignCharacterLocation(characterId: Int, locationId: Int? = null, locationName: String? = null)

    suspend fun unassignCharacterLocation(characterId: Int)

    suspend fun getAllLocations(): List<Location>

    suspend fun insertNewLocation(name: String): Long

    suspend fun getLocationById(id: Int): Location

    suspend fun getCharacterByLocation(locationId: Int): List<CharacterWithLocation>
}

class SourceRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val local: AppDao,
    private val gson: Gson
): SourceRepository {
    override suspend fun fetchCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            val characters = api.getCharacters().characters

            characters.forEach {character ->
                val characterRoom = id.novian.rickandmortycharacterlocations.data.model.room.Character(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    image = character.image,
                    originName = character.origin.name,
                    originUrl = character.origin.url,
                    locationName = character.location.name,
                    locationUrl = character.location.url,
                    created = character.created,
                    episode = gson.toJson(character.episode),
                    url = character.url,
                    locationId = null
                )
                local.insertCharacter(characterRoom)
            }

            characters
        }
    }

    override suspend fun getCharacterById(id: Int): id.novian.rickandmortycharacterlocations.data.model.room.Character? {
        return withContext(Dispatchers.IO) {
            local.getCharacterById(id)
        }
    }

    override suspend fun getCharacters(): List<id.novian.rickandmortycharacterlocations.data.model.room.Character> {
        return withContext(Dispatchers.IO) {
            local.getAllCharacter()
        }
    }

    override suspend fun assignCharacterLocation(
        characterId: Int,
        locationId: Int?,
        locationName: String?
    ) {
        return local.assignCharacterToLocation(characterId, locationId, locationName)
    }

    override suspend fun unassignCharacterLocation(characterId: Int) {
        return local.unassignCharacterFromLocation(characterId)
    }

    override suspend fun getAllLocations(): List<Location> {
        return withContext(Dispatchers.IO) {
            local.getAllLocations()
        }
    }

    override suspend fun insertNewLocation(name: String): Long {
        return withContext(Dispatchers.IO) {
            local.insertLocation(Location(name = name))
        }
    }

    override suspend fun getLocationById(id: Int): Location {
        return local.getLocationById(id)!!
    }

    override suspend fun getCharacterByLocation(locationId: Int): List<CharacterWithLocation> {
        return withContext(Dispatchers.IO) {
            local.getCharactersByLocations(locationId)
        }
    }
}