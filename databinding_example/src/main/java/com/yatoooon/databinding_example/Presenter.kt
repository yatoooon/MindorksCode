package com.yatoooon.databinding_example

import android.view.View

class Presenter {
    fun onSaveClick(task: Task){
        task.run()
    }

    fun onCompletedChanged(task: Task, completed: Boolean){
        task.run()
    }


    fun onLongClick(view: View, task: Task): Boolean {
        task.run()
        return true
    }

}