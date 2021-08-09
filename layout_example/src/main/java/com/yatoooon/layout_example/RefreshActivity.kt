package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yatoooon.coordinator_example.R

class RefreshActivity : AppCompatActivity() {
    //counting the number of swipes
    var swipeCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)

        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //getting swipeRefreshLayput from xml
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val blogs = ArrayList<Blog>()
        val adapter = CustomAdapter(blogs)

        recyclerView.adapter = adapter;
        swipeRefreshLayout.setOnRefreshListener {

            swipeCount += 1;
            if (swipeCount > 0) {
                blogs.add(
                    Blog(
                        "Blog Title $swipeCount",
                        "Description : Blog description goes here"
                    )
                )
                Toast.makeText(this, "Swipe called", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()

            swipeRefreshLayout.isRefreshing = false
        }
    }
}

data class Blog(val title: String, val desc: String)


class CustomAdapter(val blogList: ArrayList<Blog>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each layout_item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(blogList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return blogList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(blog: Blog) {
            val textViewTitle = itemView.findViewById(R.id.textViewTitle) as TextView
            val textViewDesc = itemView.findViewById(R.id.textViewDesc) as TextView
            textViewTitle.text = blog.title
            textViewDesc.text = blog.desc
        }
    }
}