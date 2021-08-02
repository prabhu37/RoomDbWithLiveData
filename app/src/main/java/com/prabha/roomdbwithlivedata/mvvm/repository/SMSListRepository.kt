package com.prabha.roomdbwithlivedata.mvvm.repository

import androidx.lifecycle.MediatorLiveData
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
import androidx.lifecycle.LiveData

/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
class SMSListRepository {

    private val smsData: MediatorLiveData<SMSEntity> = MediatorLiveData<SMSEntity>()

    companion object{
        val smsListRepository = SMSListRepository()
    }
    fun getSMSData(): LiveData<SMSEntity> {
        return smsData
    }
    fun addDataSource(data: LiveData<SMSEntity>) {
        smsData.addSource(data, smsData::setValue)
    }

    fun removeDataSource(data: LiveData<SMSEntity>) {
        smsData.removeSource(data)
    }

}