package com.example.searchgithub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val image :ImageView= findViewById(R.id.imageview2)
        val username :TextView= findViewById(R.id.username2)
        val repositoriesnumber: TextView = findViewById(R.id.repositoriesnumber2)
        val name:String = intent.getStringExtra("name")
        val number:String = intent.getStringExtra("repositoriesnumber")
        val imageview:String = intent.getStringExtra("image")

        username.setText(name)
        repositoriesnumber.setText(number)
        Picasso.with(this).load(imageview).into(image)

    }
}