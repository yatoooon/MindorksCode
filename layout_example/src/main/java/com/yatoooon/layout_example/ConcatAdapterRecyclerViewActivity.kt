package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_concat_adapter_recycler_view.*

class ConcatAdapterRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concat_adapter_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            ConcatAdapter(ConcatAdapter.Config.DEFAULT, FirstAdapter(), SecondAdapter())
    }
}

class FirstAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return 10
    }


    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.textView)
        fun bind(position: Int) {
            message.text = position.toString()
        }
    }
}

class SecondAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_2, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return 10
    }


    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.textView)
        fun bind(position: Int) {
            message.text = position.toString()
        }
    }
}