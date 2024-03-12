package id.novian.rickandmortycharacterlocations.data.source

import id.novian.rickandmortycharacterlocations.data.model.Character
import id.novian.rickandmortycharacterlocations.data.model.Characters
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