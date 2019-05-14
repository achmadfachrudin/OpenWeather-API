package com.fachrudin.base.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by achmad.fachrudin on 10-Mar-19
 */
object DateHelper {
    const val DDMMMMYYYY = "dd MMMM yyyy"
    const val EEDDMMMYYYY_HHMMZZZ = "EEE, dd MMM yyyy - HH:mm zzz"

    fun dateMessageFormat(timemillis: Long, newFormat: String): String {
        val sdf = SimpleDateFormat(newFormat, Locale("in"))
        val mDate = Date(timemillis)
        return sdf.format(mDate)
    }
}