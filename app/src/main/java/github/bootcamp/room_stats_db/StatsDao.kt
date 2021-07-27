package github.bootcamp.room_stats_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//методы для работы с данными таблицы

@Dao
interface StatsDao {

    //create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInfoStats(statsRoom: Stats_Room)

    //reade
    @Query("SELECT * FROM stats_user")
    fun readAllData(): LiveData<List<Stats_Room>>
}