package id.novian.rickandmortycharacterlocations.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Character::class, Location::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}