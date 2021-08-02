package com.prabha.roomdbwithlivedata.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
import com.prabha.roomdbwithlivedata.mvvm.repository.SMSDbRepository

/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
class SMSDbViewModel(application: Application) : AndroidViewModel(application) {
    var mRepository: SMSDbRepository = SMSDbRepository(application)
    var allMessagedLiveData: LiveData<List<SMSEntity>> = mRepository.getAllWords()
    fun getAllMessages(): LiveData<List<SMSEntity>> {
        return allMessagedLiveData
    }

    fun insert(message: SMSEntity) {
        mRepository.insert(message)
    }
}