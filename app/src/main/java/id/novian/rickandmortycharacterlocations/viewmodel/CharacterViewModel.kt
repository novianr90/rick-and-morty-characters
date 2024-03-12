package id.novian.rickandmortycharacterlocations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.rickandmortycharacterlocations.data.model.Character
import id.novian.rickandmortycharacterlocations.data.model.emptyCharacter
import id.novian.rickandmortycharacterlocations.data.repository.ApiRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repo: ApiRepository
): ViewModel() {
    private val _characters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _character: MutableStateFlow<Character> = MutableStateFlow(emptyCharacter)
    val character: StateFlow<Character> = _character

    init {
        fetchCharacters()
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
}