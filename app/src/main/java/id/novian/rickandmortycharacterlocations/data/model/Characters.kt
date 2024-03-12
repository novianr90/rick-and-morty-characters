package id.novian.rickandmortycharacterlocations.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)

val characterSample = Character(
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    type = "",
    gender = "Male",
    origin = Origin(
        name = "Earth (C-137)",
        url = "https://rickandmortyapi.com/api/location/1"
    ),
    location = Location(
        name = "Citadel of Ricks",
        url = "https://rickandmortyapi.com/api/location/3"
    ),
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    id = 1,
    created = "2017-11-04T18:48:46.250Z",
    url = "https://rickandmortyapi.com/api/character/1",
    episode = listOf()
)

val emptyCharacter = Character(
    name = "",
    status = "",
    species = "",
    type = "",
    gender = "",
    origin = Origin(
        name = "",
        url = ""
    ),
    location = Location(
        name = "",
        url = ""
    ),
    image = "",
    id = 1,
    created = "",
    url = "",
    episode = listOf()
)