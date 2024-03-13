package id.novian.rickandmortycharacterlocations.data.model.network


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)