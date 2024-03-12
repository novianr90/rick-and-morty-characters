package id.novian.rickandmortycharacterlocations.screen

import id.novian.rickandmortycharacterlocations.data.model.Character

sealed class Screen(val route: String) {
    data object CharacterList: Screen("character_list")
    data class DetailCharacter(val character: Character) : Screen("character_details")
}