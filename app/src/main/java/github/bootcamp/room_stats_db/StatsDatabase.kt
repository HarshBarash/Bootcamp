package github.bootcamp.room_stats_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Сама БД

@Database(entities = [Stats_Room::class], version = 1, exportSchema = true)
abstract class StatsDatabase : RoomDatabase() {
    abstract fun statsDao(): StatsDao

    companion object {
        @Volatile //видно на лету
        private var INSTANCE: StatsDatabase? = null

        fun getDatabase(context: Context): StatsDatabase {
            val tempInstance = INSTANCE
            //если существует работаем
            if (tempInstance != null) {
                return tempInstance
            }
            //если не существует создаём
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatsDatabase::class.java,
                    "stats_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}