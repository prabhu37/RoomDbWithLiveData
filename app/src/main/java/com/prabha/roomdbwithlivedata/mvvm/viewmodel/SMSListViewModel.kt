package com.prabha.roomdbwithlivedata.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
import com.prabha.roomdbwithlivedata.mvvm.repository.SMSListRepository

/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
class SMSListViewModel(application: Application) : AndroidViewModel(application) {
    fun getData(): LiveData<SMSEntity> {
        return SMSListRepository.smsListRepository.getSMSData()
    }

}