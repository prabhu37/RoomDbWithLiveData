package com.prabha.roomdbwithlivedata.view

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.prabha.roomdbwithlivedata.R
import com.prabha.roomdbwithlivedata.databinding.ActivityMainBinding
import com.prabha.roomdbwithlivedata.mvvm.repository.SMSListRepository
import com.prabha.roomdbwithlivedata.receiver.SMSReceiver
import com.prabha.roomdbwithlivedata.utils.Constants.Util.action_SMS_RECEIVER
import com.prabha.roomdbwithlivedata.mvvm.viewmodel.SMSListViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prabha.roomdbwithlivedata.adapter.SmsListAdapter
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
import com.prabha.roomdbwithlivedata.mvvm.viewmodel.SMSDbViewModel
import com.google.gson.Gson
/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var smsListViewModel: SMSListViewModel
    lateinit var smsDbViewModel:SMSDbViewModel
     lateinit var receiver: SMSReceiver
     lateinit var smsListAdapter: SmsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }


    override fun onDestroy() {
        super.onDestroy()
        SMSListRepository.smsListRepository.removeDataSource(receiver.getSMSResponseData())
        unregisterReceiver(receiver)
    }

    private fun init() {
        setViewModel()
        setUI()
    }

    private fun setUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMessageList)
        smsListAdapter = SmsListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = smsListAdapter

    }

    private fun setViewModel() {
        receiver = SMSReceiver()
        SMSListRepository.smsListRepository.addDataSource(receiver.getSMSResponseData())
        registerReceiver(receiver, IntentFilter(action_SMS_RECEIVER))
        smsListViewModel = ViewModelProvider(this).get(SMSListViewModel::class.java)
        smsDbViewModel = ViewModelProvider(this).get(SMSDbViewModel::class.java)
        smsListViewModel.getData().observe(this, {
            smsDbViewModel.insert(it)
            Log.e("Message11", it.message)
        })
        smsDbViewModel.getAllMessages().observe(this,{
            Log.e("MessagesDb :",Gson().toJson(it)+" null")
            smsListAdapter.submitList(messageList = it as MutableList<SMSEntity>)
        })



    }
}

