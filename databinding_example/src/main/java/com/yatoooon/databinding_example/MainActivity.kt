package com.yatoooon.databinding_example

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import com.yatoooon.databinding_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        //fragment recyclerview binding
        //  val listItemBinding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
        //  val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false)
        binding.text = "test"
        binding.user = User("yatoooon", 18)
        binding.handlers = MyHandlers()


        binding.task = Task()
        binding.presenter = Presenter()

        val listOf = listOf<User>(User("111", 11), User("222", 22), User("333", 33))
        binding.rvUser.adapter = UserAdapter(listOf)


        binding.isChecked = false

        binding.btSxbd.setOnClickListener { binding.isChecked = !binding.cbSxbd.isChecked }

    }


}