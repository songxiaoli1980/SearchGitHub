package com.example.searchgithub

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.text.Html
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView
import org.json.*;
class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRecyclerView = findViewById(R.id.recycleview) as RecyclerView
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        val url = "https://api.github.com/users"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                val image = response.getJSONArray("avatar_url")
                val username = response.getJSONArray("login")
                val repositoriesnumber = response.getJSONArray("url")


                newsRecyclerView.adapter = NewsAdapter(image,username,repositoriesnumber)

            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            })

        VolleyService.requestQueue.add(request)
        VolleyService.requestQueue.start()
    }

    class NewsAdapter(val image: JSONArray,val username: JSONArray, val repositoriesnumber: JSONArray) : RecyclerView.Adapter<NewsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.news_item, parent, false)
            return NewsViewHolder(view)
        }
        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(image.getJSONObject(position), position)
            holder.bind(username.getJSONObject(position), position)
            holder.bind(repositoriesnumber.getJSONObject(position), position)
        }

        override fun getItemCount(): Int = username.length()
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(newsItem: JSONObject, position: Int) {
            val username = view.findViewById(R.id.username) as TextView
            val repositoriesnumber = view.findViewById(R.id.repositoriesnumber) as TextView
            val image = view.findViewById(R.id.image) as NetworkImageView
            username.text = newsItem["login"].toString()
            repositoriesnumber.text = newsItem["avatar_url"].toString()
            image.setImageUrl(
                newsItem["url"].toString(),
                VolleyService.imageLoader)
        }
    }
}

