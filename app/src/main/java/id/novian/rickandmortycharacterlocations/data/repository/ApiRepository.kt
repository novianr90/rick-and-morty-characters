package id.novian.rickandmortycharacterlocations.data.repository

import id.novian.rickandmortycharacterlocations.data.model.Character
import id.novian.rickandmortycharacterlocations.data.source.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ApiRepository {
    suspend fun getCharacters(): List<Character>
}

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiService
): ApiRepository {
    override suspend fun getCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            api.getCharacters().characters
        }
    }
}