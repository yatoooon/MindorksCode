package com.yatoooon.databinding_example

class Task {
    fun run(){
        println("Task RUN")
    }

    companion object {
        @JvmStatic
        fun runCount(): String {
            return "runCount"
        }
    }
}