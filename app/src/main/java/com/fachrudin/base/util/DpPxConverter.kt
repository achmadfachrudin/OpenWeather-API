package com.fachrudin.base.util

import android.content.Context

/**
 * Created by achmad.fachrudin on 25-Apr-19
 */
object DpPxConverter {
    fun dpToPx(context: Context, dp: Int): Int {
        return (dp *  context.resources.displayMetrics.density).toInt()
    }

    fun pxToDp(context: Context, px: Int): Int {
        return (px /  context.resources.displayMetrics.density).toInt()
    }
}