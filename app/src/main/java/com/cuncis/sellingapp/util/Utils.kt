package com.cuncis.sellingapp.util

import android.content.Context
import android.util.Log
import android.widget.Toast

class Utils {
    companion object {
        fun showLog(message: String) {
            Log.d("_logSelling", "" + message)
        }

        fun Context.showToast(message: String) {
            Toast.makeText(applicationContext, "" + message, Toast.LENGTH_SHORT).show()
        }
    }
}