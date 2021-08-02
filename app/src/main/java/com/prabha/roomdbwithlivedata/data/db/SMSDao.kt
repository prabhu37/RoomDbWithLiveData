package com.prabha.roomdbwithlivedata.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
@Dao
interface SMSDao {

    @Query("SELECT * from sms_table")
    fun getAllMessages(): LiveData<List<SMSEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMessage(message: SMSEntity)
}
