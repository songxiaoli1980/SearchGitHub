package com.example.searchgithub

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley


object VolleyService {
     private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context) }

    val imageLoader: ImageLoader by lazy { ImageLoader(requestQueue, LruBitmapCache()) }
}