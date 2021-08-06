package com.yatoooon.mindorkscode

class DesignPatterns {


    fun main(args: Array<String>) {
        //创造模式
        //1 构造者模式
        Laptop.Builder("i7")
            .setRam("8GB")
            .setBattery("6000MAH")
            .create()
        //2 单例模式
        DataManager.getUserData()
        //3 依赖注入 dagger2  koin  hilt都是

        //结构模式
        //1 适配器模式
        //2 门面模式

        //行为模式
        //1 观察者模式
        //2 MVC MVP MVVM


    }


}


//Builder pattern
class Laptop(builder: Builder) {
    private val processor: String = builder.processor
    private val ram: String = builder.ram
    private val battery: String = builder.battery
    private val screenSize: String = builder.screenSize

    // Builder class
    class Builder(processor: String) {
        var processor: String = processor // this is necessary

        // optional features
        var ram: String = "2GB"
        var battery: String = "5000MAH"
        var screenSize: String = "15inch"

        fun setRam(ram: String): Builder {
            this.ram = ram
            return this
        }

        fun setBattery(battery: String): Builder {
            this.battery = battery
            return this
        }

        fun setScreenSize(screenSize: String): Builder {
            this.screenSize = screenSize
            return this
        }

        fun create(): Laptop {
            return Laptop(this)
        }
    }
}

//Singleton pattern
object DataManager {
    init {
        println("Singleton class invoked.")
    }

    fun getUserData() {
        // some code
    }
}


