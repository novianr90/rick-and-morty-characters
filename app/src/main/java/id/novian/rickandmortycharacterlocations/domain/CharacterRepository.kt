package id.novian.rickandmortycharacterlocations.domain

import id.novian.rickandmortycharacterlocations.data.model.room.CharacterWithLocation
import id.novian.rickandmortycharacterlocations.data.model.room.Location
import id.novian.rickandmortycharacterlocations.data.repository.SourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterDomain>
    suspend fun getCharacterById(id: Int): CharacterDomain

    suspend fun assignCharacterToExistingLocation(characterId: Int, locationId: Int)
    suspend fun assignCharacterToNewLocation(characterId: Int, locationName: String)
    suspend fun unassignCharacterFromLocation(characterId: Int)

    suspend fun getAllLocations(): List<Location>

    suspend fun createNewLocation(name: String): Long

    suspend fun getLocationById(id: Int): Location

    suspend fun getCharacterByLocation(locationId: Int): List<CharacterWithLocation>
}

class CharacterRepositoryImpl(
    private val source: SourceRepository
): CharacterRepository {

    private var fetched = false
    override suspend fun getCharacters(): List<CharacterDomain> {
        return withContext(Dispatchers.IO) {

            if (!fetched) {
                source.fetchCharacters()
                fetched = true
            }

            val characters = source.getCharacters()

            val characterDomains = characters.map { character ->
                CharacterDomain(
                    id = character.id,
                    name = character.name,
                    image = character.image,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    origin = character.originName
                )
            }

            characterDomains
        }
    }

    override suspend fun getCharacterById(id: Int): CharacterDomain {
        return withContext(Dispatchers.IO) {
            val character = source.getCharacterById(id)
            if (character != null) {
                CharacterDomain(
                    id = character.id,
                    name = character.name,
                    image = character.image,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    origin = character.originName
                )
            } else {
                CharacterDomain()
            }
        }
    }

    override suspend fun assignCharacterToExistingLocation(characterId: Int, locationId: Int) {
        return source.assignCharacterLocation(characterId, locationId)
    }

    override suspend fun assignCharacterToNewLocation(characterId: Int, locationName: String) {
        return source.assignCharacterLocation(characterId, locationName = locationName)
    }

    override suspend fun unassignCharacterFromLocation(characterId: Int) {
        return source.unassignCharacterLocation(characterId)
    }

    override suspend fun getAllLocations(): List<Location> {
        return source.getAllLocations()
    }

    override suspend fun createNewLocation(name: String): Long {
        return source.insertNewLocation(name)
    }

    override suspend fun getLocationById(id: Int): Location {
        return withContext(Dispatchers.IO) {
            source.getLocationById(id)
        }
    }

    override suspend fun getCharacterByLocation(locationId: Int): List<CharacterWithLocation> {
        return source.getCharacterByLocation(locationId)
    }
}