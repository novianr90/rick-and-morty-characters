package id.novian.rickandmortycharacterlocations.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.novian.rickandmortycharacterlocations.data.repository.ApiRepository
import id.novian.rickandmortycharacterlocations.data.repository.ApiRepositoryImpl
import id.novian.rickandmortycharacterlocations.data.source.ApiService
import id.novian.rickandmortycharacterlocations.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }
}