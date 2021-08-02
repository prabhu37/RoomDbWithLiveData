package com.prabha.roomdbwithlivedata.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.prabha.roomdbwithlivedata.R
import com.prabha.roomdbwithlivedata.data.db.entity.SMSEntity
import kotlinx.android.synthetic.main.item_sms.view.*
/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
class SmsListAdapter( var context: Context) :
    RecyclerView.Adapter<SmsListAdapter.SmsListViewHolder>() {


    override fun onBindViewHolder(holder: SmsListViewHolder, position: Int) {
        val friendsDetails: SMSEntity = messages.currentList[position]
        holder.bindItems(friendsDetails, context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sms, parent, false)
        return SmsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.currentList.size

    }

    fun submitList(messageList: List<SMSEntity>) {
        messages.submitList(messageList)
    }

    private val DIFF_CALLBACK: DiffUtil.ItemCallback<SMSEntity> =
        object : DiffUtil.ItemCallback<SMSEntity>() {
            override fun areItemsTheSame(
                oldProduct: SMSEntity,
                newProduct: SMSEntity
            ): Boolean {
                return oldProduct.time.equals(newProduct.time)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldProduct: SMSEntity,
                newProduct: SMSEntity
            ): Boolean {
                return oldProduct.time === newProduct.time
            }
        }
    var messages: AsyncListDiffer<SMSEntity> = AsyncListDiffer(this, DIFF_CALLBACK)
    class SmsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var context: Context? = null
        fun bindItems(smsDetails: SMSEntity, context: Context) {
            itemView.textSender.text = smsDetails.number
            itemView.textmessage.text = smsDetails.message
            itemView.textTime.text = smsDetails.time
            this.context = context
            itemView.itemParent.setOnClickListener(clickListener)


        }

        private val clickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                itemView.itemParent.id -> {

                }

            }
        }
    }
}

