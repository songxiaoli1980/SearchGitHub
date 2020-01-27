package com.example.searchgithub

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by vamsitallapudi on 16/01/18.
 */
class ListRecyclerAdapter(private val userlist: ArrayList<User>): RecyclerView.Adapter<ListRecyclerAdapter.NewsHolder>() {

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ListRecyclerAdapter.NewsHolder, position: Int) {
        val itemNews = userlist[position]
        holder.bindNews(itemNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRecyclerAdapter.NewsHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.news_item, parent, false)
        return NewsHolder(view)
    }


    class NewsHolder(v:View) : RecyclerView.ViewHolder(v) , View.OnClickListener {

        private var view : View = v
        private var userlist : User? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")

            val context = itemView.context
            val showPhotoIntent = Intent(context,SecondActivity::class.java )
            showPhotoIntent.putExtra("name", userlist?.username)
            showPhotoIntent.putExtra("repositoriesnumber", userlist?.repositoriesnumber)
            showPhotoIntent.putExtra("image", userlist?.image)
            context.startActivity(showPhotoIntent)
        }

        fun bindNews(userlist: User) {
            this.userlist = userlist
            Picasso.with(view.context).load(userlist.image).into(view.imageview)
            view.username.text = userlist.username
            view.repositoriesnumber.text = userlist.repositoriesnumber
        }
    }
}