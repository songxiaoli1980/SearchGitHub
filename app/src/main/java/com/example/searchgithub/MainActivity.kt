package com.example.searchgithub

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest
import org.json.*;
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var userlist :ArrayList<User> = ArrayList<User>(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRecyclerView = findViewById(R.id.recycleview) as RecyclerView
        val editTextSearch :EditText= findViewById(R.id.editTextSearch);
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        val requestQueue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/users"
        val request = JsonArrayRequest(Request.Method.GET, url,null,
            Response.Listener<JSONArray> { response ->
                for (i in 0 until response.length()){
                    val obj = response.getJSONObject(i)
                    val user = User()
                    user.image = obj.getString("avatar_url")
                    user.username = obj.getString("login")
                    user.repositoriesnumber = obj.getString("url")
                    userlist.add(user)
                }
                newsRecyclerView.adapter = ListRecyclerAdapter(userlist)
            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(request)
    }
}

