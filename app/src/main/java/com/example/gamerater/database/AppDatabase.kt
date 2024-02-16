package com.example.gamerater.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamerater.dao.GameDao
import com.example.gamerater.model.Game

@Database(entities = [Game::class], version = 2)
abstract class AppDatabase(): RoomDatabase(){
    companion object{
        val DATABASE_NAME = "library"
    }

    abstract fun gameDao(): GameDao

}
