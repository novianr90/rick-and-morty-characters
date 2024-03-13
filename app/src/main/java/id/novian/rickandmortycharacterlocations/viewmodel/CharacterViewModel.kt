package id.novian.rickandmortycharacterlocations.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.rickandmortycharacterlocations.data.model.room.AppDao
import id.novian.rickandmortycharacterlocations.data.model.room.CharacterWithLocation
import id.novian.rickandmortycharacterlocations.data.model.room.Location
import id.novian.rickandmortycharacterlocations.data.repository.SourceRepository
import id.novian.rickandmortycharacterlocations.domain.CharacterDomain
import id.novian.rickandmortycharacterlocations.domain.CharacterRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repo: CharacterRepository
): ViewModel() {
    private val _characters: MutableStateFlow<List<CharacterDomain>> = MutableStateFlow(emptyList())
    val characters: StateFlow<List<CharacterDomain>> = _characters

    private val _character: MutableStateFlow<CharacterDomain> = MutableStateFlow(CharacterDomain())
    val character: StateFlow<CharacterDomain> = _character

    private val _locations: MutableStateFlow<List<Location>> = MutableStateFlow(emptyList())
    val locations: StateFlow<List<Location>> = _locations

    private val _location: MutableStateFlow<Location> = MutableStateFlow(Location(name = ""))
    val location: StateFlow<Location> = _location

    private val _locationsList: MutableStateFlow<List<CharacterWithLocation>> = MutableStateFlow(emptyList())
    val locationList: StateFlow<List<CharacterWithLocation>> = _locationsList

    init {
        fetchCharacters()
        getLocations()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _characters.value = repo.getCharacters()
        }
    }

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch {
            _character.value = repo.getCharacterById(id)
        }
    }

    fun assignCharacterToExistingLocation(characterId: Int, locationId: Int) {
        viewModelScope.launch {
            repo.assignCharacterToExistingLocation(characterId, locationId)
        }
    }

    fun assignCharacterToNewLocation(characterId: Int, locationName: String) {
        viewModelScope.launch {
            repo.assignCharacterToNewLocation(characterId, locationName)
        }
    }

    fun unassignCharacterLocation(characterId: Int) {
        viewModelScope.launch {
            repo.unassignCharacterFromLocation(characterId)
        }
    }

    fun getLocations() {
        viewModelScope.launch {
            _locations.value = repo.getAllLocations()
        }
    }

    fun createNewLocation(name: String) {
        viewModelScope.launch {
            repo.createNewLocation(name)
        }
    }

    fun getLocationById(id: Int) {
        viewModelScope.launch {
            _location.value = repo.getLocationById(id)
        }
    }

    fun getCharacterByLocationId(locationId: Int) {
        viewModelScope.launch {
            _locationsList.value = repo.getCharacterByLocation(locationId)
        }
    }
}