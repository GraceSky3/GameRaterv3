package com.example.gamerater.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gamerater.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun list(): List<Game>

    @Query("DELETE FROM game WHERE title=:title ")
    fun delete(title: String): Int

    @Query("SELECT * FROM game WHERE review=:review")
    fun listReviewsGames(review:String): List<Game>
    //fun listCategoryGames(category: String): List<Game>

    @Insert
    fun save(game:Game)

    @Query("SELECT * FROM game where title=:title")
    fun findById (title: String): Game?
}