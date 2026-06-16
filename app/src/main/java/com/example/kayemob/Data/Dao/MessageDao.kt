package com.example.kayemob.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kayemob.Data.Entity.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages")
    suspend fun getAll(): List<MessageEntity>

    @Insert
    suspend fun insert(message: MessageEntity)
}
