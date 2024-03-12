package id.novian.rickandmortycharacterlocations.data.source

import id.novian.rickandmortycharacterlocations.data.model.Characters
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Characters
}