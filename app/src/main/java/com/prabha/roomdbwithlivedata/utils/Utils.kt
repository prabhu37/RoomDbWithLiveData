package com.prabha.roomdbwithlivedata.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by prabhakaranpanjalingam on 02,August,2021
 */
object Utils {
    fun getDateCurrentTimeZone(timestamp: Long): String {
        try {
            val calendar: Calendar = Calendar.getInstance()
            val tz: TimeZone = TimeZone.getDefault()
            calendar.setTimeInMillis(timestamp * 1000)
            val sdf = SimpleDateFormat("EEEE, d MMM yyyy - hh:mm a", Locale.getDefault())
            val currenTimeZone: Date = calendar.getTime() as Date
            return sdf.format(currenTimeZone)
        } catch (e: Exception) {
        }
        return ""
    }
}