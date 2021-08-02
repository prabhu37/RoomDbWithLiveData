package com.prabha.roomdbwithlivedata.mvvm.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.prabha.roomdbwithlivedata.data.db.SMSDao
import com.prabha.roomdbwithlivedata.data.db.SMSRoomDatabase
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity

/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
class SMSDbRepository(application: Application) {
     var smsDao: SMSDao
     var allMessagedLiveData: LiveData<List<SMSEntity>>
    val smsDbRepository  = SMSRoomDatabase.getDatabase(application)

   init {
       smsDao = smsDbRepository.smsDao()
       allMessagedLiveData = smsDao.getAllMessages()
   }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    fun getAllWords(): LiveData<List<SMSEntity>> {
        return allMessagedLiveData
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(smsEntity: SMSEntity) {
        SMSRoomDatabase.databaseWriteExecutor.execute { smsDao.insertMessage(smsEntity) }
    }
}