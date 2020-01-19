package com.cuncis.sellingapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.cuncis.sellingapp.R

class Utils {
    companion object {
        fun showLog(message: String) {
            Log.d("_logSelling", message)
        }

        fun Context.showToast(message: String) {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
        fun Context.setGlideImage(imageView: ImageView, imageurl: String, progressBar: ProgressBar) {
            Glide.with(applicationContext).load(imageurl).placeholder(R.drawable.img_no_image).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

            }).into(imageView)
        }
    }
}