package id.novian.rickandmortycharacterlocations.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.novian.rickandmortycharacterlocations.data.model.room.AppDao
import id.novian.rickandmortycharacterlocations.data.model.room.AppDatabase
import id.novian.rickandmortycharacterlocations.data.repository.SourceRepository
import id.novian.rickandmortycharacterlocations.data.repository.SourceRepositoryImpl
import id.novian.rickandmortycharacterlocations.data.source.ApiService
import id.novian.rickandmortycharacterlocations.domain.CharacterRepository
import id.novian.rickandmortycharacterlocations.domain.CharacterRepositoryImpl
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
    fun provideApiRepository(apiService: ApiService, appDao: AppDao, gson: Gson): SourceRepository {
        return SourceRepositoryImpl(apiService, appDao, gson)
    }

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "rick-morty-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesDao(appDatabase: AppDatabase): AppDao = appDatabase.appDao()

    @Provides
    fun provideCharacterRepository(sourceRepository: SourceRepository): CharacterRepository {
        return CharacterRepositoryImpl(sourceRepository)
    }
}