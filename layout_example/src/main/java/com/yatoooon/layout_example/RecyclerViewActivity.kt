package com.yatoooon.layout_example

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yatoooon.coordinator_example.R


class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_layout)
        val dataList = ArrayList<Data>()
        dataList.add(Data(1, RecyclerViewAdapter.VIEW_TYPE_ONE, "1. Hi! I am in View 1"))
        dataList.add(Data(2, RecyclerViewAdapter.VIEW_TYPE_TWO, "2. Hi! I am in View 2"))
        dataList.add(
            Data(
                3,
                RecyclerViewAdapter.VIEW_TYPE_ONE,
                "1111111111111. Hi! I am in View 3"
            )
        )
        dataList.add(Data(4, RecyclerViewAdapter.VIEW_TYPE_TWO, "4. Hi! I am in View 4"))
        dataList.add(Data(5, RecyclerViewAdapter.VIEW_TYPE_ONE, "5. Hi! I am in View 5"))
        dataList.add(Data(6, RecyclerViewAdapter.VIEW_TYPE_TWO, "6. Hi! I am in View 6"))
        dataList.add(Data(7, RecyclerViewAdapter.VIEW_TYPE_ONE, "7. Hi! I am in View 7"))
        dataList.add(Data(8, RecyclerViewAdapter.VIEW_TYPE_TWO, "8. Hi! I am in View 8"))
        dataList.add(Data(9, RecyclerViewAdapter.VIEW_TYPE_ONE, "9. Hi! I am in View 9"))
        dataList.add(Data(10, RecyclerViewAdapter.VIEW_TYPE_TWO, "10. Hi! I am in View 10"))
        dataList.add(Data(11, RecyclerViewAdapter.VIEW_TYPE_ONE, "11. Hi! I am in View 11"))
        dataList.add(Data(12, RecyclerViewAdapter.VIEW_TYPE_TWO, "12. Hi! I am in View 12"))

        val adapter = RecyclerViewAdapter()
        recyclerView = findViewById(R.id.rvChapterList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.submitList(dataList)

        val dataList2 = ArrayList<Data>()
        Thread() {
            Thread.sleep(2000)
            for (i in 1..100) {
                dataList2.add(
                    (Data(
                        i,
                        if (i % 2 == 1) RecyclerViewAdapter.VIEW_TYPE_ONE else RecyclerViewAdapter.VIEW_TYPE_TWO,
                        "$i. Hi! I am in View $i"
                    ))
                )
            }
            adapter.submitList(dataList2)
        }.start()

    }


}

class RecyclerViewAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val diffCallback: DiffUtil.ItemCallback<Data> =
        object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                val (_, type1, text1) = oldItem
                val (_, type2, text2) = newItem
                return type1 == type2 && text1 == text2
            }

            override fun getChangePayload(oldItem: Data, newItem: Data): Any? {
                val payload = Bundle()
                if (!TextUtils.equals(oldItem.textData, newItem.textData)) {
                    payload.putString("KEY_textData", newItem.textData)
                }
                if (payload.size() == 0)//如果没有变化 就传空
                    return null
                return payload
            }
        }
    private var mDiffer: AsyncListDiffer<Data> = AsyncListDiffer(this, diffCallback)


    fun submitList(data: List<Data>) {
        mDiffer.submitList(data)
    }

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_view_1, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }


    fun getItem(position: Int): Data {
        return mDiffer.currentList[position]
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val any: Bundle = payloads[0] as Bundle
            for (key in any.keySet()) {
                when (key) {
                    "KEY_textData" -> {
                        if (getItem(position).viewType == VIEW_TYPE_ONE) {
                            (holder as View1ViewHolder).message.text = any.get(key).toString()
                        } else if (getItem(position).viewType == VIEW_TYPE_TWO) {
                            (holder as View2ViewHolder).message.text = any.get(key).toString()
                        }
                    }
                }
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItem(position).viewType == VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else if (getItem(position).viewType == VIEW_TYPE_TWO) {
            (holder as View2ViewHolder).bind(position)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.textView)
        fun bind(position: Int) {
            val recyclerViewModel = getItem(position)
            message.text = recyclerViewModel.textData
        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.textView)
        fun bind(position: Int) {
            val recyclerViewModel = getItem(position)
            message.text = recyclerViewModel.textData
        }
    }


}

data class Data(val id: Int, val viewType: Int, val textData: String)

