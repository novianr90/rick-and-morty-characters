package id.novian.rickandmortycharacterlocations.domain

import id.novian.rickandmortycharacterlocations.data.model.network.Character
import id.novian.rickandmortycharacterlocations.data.model.network.Location
import id.novian.rickandmortycharacterlocations.data.model.network.Origin

data class CharacterDomain(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val status: String? = null,
    val species: String? = null,
    val gender: String? = null,
    val origin: String? = null
)

val characterSample = CharacterDomain(
    id = 1,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    gender = "Male",
    origin = "Earth (C-137)"
)
