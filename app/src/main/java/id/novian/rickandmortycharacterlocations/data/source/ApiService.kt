package id.novian.rickandmortycharacterlocations.data.source

import id.novian.rickandmortycharacterlocations.data.model.network.Character
import id.novian.rickandmortycharacterlocations.data.model.network.Characters
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Characters

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Character
}