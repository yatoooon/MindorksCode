package com.yatoooon.mindorkscode

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope { // CoroutineScope 协程作用域
    private val TAG = "MainActivity"
    private var tvText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        tvText = findViewById<TextView>(R.id.text)
        // 打印
        print("Amit Shekhar")
        println("Amit Shekhar")
        // 常量和变量
        var name1 = "Amit Shekhar"
        val name2 = "Amit Shekhar" // 不可变  只可以被分配一次
        // null值
        val otherName: String?
        otherName = null
        // 判断不为null
        val length = otherName?.length
        // 判断不为null或者不为empty
        val sampleString = "Shekhar"
        if (sampleString.isNotEmpty()) { // kotlin扩展函数
            text.text = sampleString
        }
        // 字符串拼接
        val firstName = "Amit"
        val lastName = "Shekhar"
        var message = "My name is: $firstName $lastName"
        // 换行
        val text1 =
            """
        |First Line
        |Second Line
        |Third Line
        """.trimMargin()
        // 截取字符串
        val str = "Java to Kotlin Guide"
        val sub = str.substring(0..3)
        println("substring $sub")
        // kotlin没有三元运算
        val x = 10
        val test = if (x > 5) "x > 5" else "x <= 5"
        val test2: String? = null
        println(test2 ?: "aaa") // 当test2为null时返回aaa
        println(test2?.length) // 当test2不为null时返回length
        // 按位运算
        val a = 1
        val b = 2
        val andResult = a and b
        val orResult = a or b
        val xorResult = a xor b
        val rightShift = a shr 2
        val leftShift = a shl 2
        val unsignedRightShift = a ushr 2
        // 判断类型和类型转换
        val obj: Any? = null
        if (obj is View) {
            var view = obj // 显示转换
        }
        if (obj is View) {
            var view = obj // 智能转换
        }
//        val view1 = obj as View //obj是View类型转换为View 如果obj为null 会崩溃
        val view2 = obj as? View // obj是View类型转换为View  obj不是View就返回null  // var view = obj as View?
        println("view： --- $view2")
        // 多重条件
        var score = 65
        if (score in 60..80) {
            println("及格")
        }
        score = 8
        // 多重条件（when相当于java语言里面的switch）
        var grade = when (score) {
            9, 10 -> "Excellent"
            in 6..8 -> "Good"
            4, 5 -> "OK"
            else -> "Fail"
        }
        // 集合
        val listOfNumber = listOf(1, 2, 3, 4)
        val keyValue = mapOf(
            1 to "Amit",
            2 to "Ali",
            3 to "Mindorks"
        )
        // for循环
        for (i in 1..10) {
        }

        for (i in 1 until 10) {
        }

        for (i in 10 downTo 0) {
        }

        for (i in 1..10 step 2) {
        }

        for (i in 10 downTo 0 step 2) {
        }

        for (item: Int in listOfNumber) {
        }

        for ((key: Int, value: String) in keyValue) {
        }
        // foreach
        listOfNumber.forEach {
            println(it)
        }
        listOfNumber.filter { it == 3 }.forEach {
            println(it)
        }
        // 拆分数组
        val (param, value) = "param=car".split("=")

        start()
        performLetOperation()

        label()


        //DSL
        data class Student(
            var name: String? = null,
            var age: Int? = null,
            var marks: Int? = null
        )
        val student = Student(
            name = "MindOrks",
            age = 20,
            marks = 30
        )
        tvText?.apply{
            text = "MindOrks"
            setOnClickListener {

            }
            setTextColor(Color.BLACK)
        }
        class Json() : JSONObject() {
            constructor(json: Json.() -> Unit) : this() {
                this.json()
            }
            infix fun <T> String.to(value: T) {
                put(this, value)
            }
        }
        val json = Json {
            "name" to "MindOrks"
            "age" to 20
        }
        println(json)

    }

    // 方法
    fun doSomeThing() {
    }

    // 方法默认参数
    fun calculateCost(quantity: Int, pricePerItem: Double = 20.5) = quantity * pricePerItem

    // 方法可变参数
    fun doSomething(vararg numbers: Int) {
    }

    // 方法返回值
    fun getScore(value: Int) = 2 * value

    // 构造方法和静态方法（类方法）
    class Utils1 private constructor() {
        companion object {
            fun getScore(value: Int): Int {
                return 2 * value
            }
        }
    }

    object Utils2 {
        fun getScore(value: Int): Int {
            return 2 * value
        }
    }

    // get set
    data class Developer(var name: String, var age: Int)

    // cloning or copying
    val dev = Developer("Mindorks", 30)
    val dev2 = dev.copy()
    val dev3 = dev.copy(age = 25)

    // 泛型
    interface SomeInterface1<T> {
        fun doSomething(data: T)
    }

    class SomeClass1 : SomeInterface1<String> {
        override fun doSomething(data: String) {
            // some logic
        }
    }

    interface SomeInterface2<T : Collection<*>> {
        fun doSomething(data: T)
    }

    class SomeClass2 : SomeInterface2<List<String>> {
        override fun doSomething(data: List<String>) {
            // some logic
        }
    }

    // 扩展函数
    fun Int.triple(): Int {
        return this * 3
    }

    var i = 3.triple()

    // 定义的时候未实例化
    internal lateinit var utils1: Utils1

    // 枚举
    enum class Direction(val direction: Int) {
        NORTH(1),
        SOUTH(2),
        WEST(3),
        EAST(4);
    }

    // 排序
    val developers =
        listOf(
            Developer("yangdong", 27),
            Developer("zhangsan", 18),
            Developer("zhangsan", 18),
            Developer("lisi", 20)
        )

    fun sort(developers: List<Developer>) {
        developers.sortedWith(Comparator { o1, o2 -> o1.age - o2.age })
    }

    fun onClick(v: View) {
        println("onClick--------2")
    }

    fun clickExample() {
        // 匿名类
        tvText?.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    println("onClick--------1")
                }
            }
        )
        // 高阶函数
        tvText?.setOnClickListener(::onClick)
        tvText?.setOnClickListener(
            fun(v: View) {
                println("onClick--------3")
            }
        )
        // Lambda   匿名类的简化
        tvText?.setOnClickListener {
            println("onClick--------4")
        }
        // 自己设计回调
        setOnClickListener(::onClick)
    }

    // 设计回调
    var onclick: (View) -> Unit = {} //  var onclick: ((View) -> Unit)? = null
    fun setOnClickListener(onClick: (View) -> Unit) {
        this.onclick = onClick
    }

    // 初始化
    init {
        print("init block")

        val instance = YourManager.getInstance(application)
//            .doSomething()  //单例使用demo
    }

    // lateinit 延迟初始化 确保在用之前初始化 不然会报错
    private lateinit var courseName: String

    // demo function to get course name using the courseId
    fun fetchCourseName() {
        courseName = "A"

        // 使用变量之前，如果不确定初始化，可以使用下面来判断
        if (this::courseName.isInitialized) {
            // access courseName
        } else {
            // some default value
        }
    }

    // lazy
    private val user: Developer = Developer("user", 20)
    private val lazyUser: Developer by lazy { Developer("LazyUser", 20) }

    // sealed 密封类
    sealed class Result {
        data class Success(val msg: String) : Result()
        data class Error(val exception: Exception) : Result()
    }

    // 内联 inline   noline  crossinline
    fun doSomething() {
        print("doSomething start")
        doSomethingElse()
        doSomethingElse {
            println("doSomethingElse------2")
        }
        doSomethingElse(
            abc = {
                println("doSomethingElse------3")
            },
            xyz = {
                println("doSomethingElse------4")
            }
        )
        crossinline_doSomethingElse {
            // 设置了crossinline  return在编译期就会报错   不然return会影响后面代码的执行
//            return
        }
        print("doSomething end")
    }

    // 普通内联
    inline fun doSomethingElse() {
        print("doSomethingElse------1")
    }

    // 高阶内联
    inline fun doSomethingElse(abc: () -> Unit) {
        abc()
    }

    // 一个内联一个不内联
    inline fun doSomethingElse(abc: () -> Unit, noinline xyz: () -> Unit) {
        abc()
        xyz()
    }

    // crossinline 解决lambda  return的问题
    inline fun crossinline_doSomethingElse(crossinline abc: () -> Unit) {
        abc()
    }

    // 协程
    data class User(var name: String)

    suspend fun fetchUser(): User { // suspend 仅允许从协程或其他挂起函数调用挂起函数 所以fetchAndShowUser()也是挂起函数
        return GlobalScope.async(Dispatchers.IO) { // async 使用await()返回 Deferred<T>
            println("currentThread-----" + Thread.currentThread().id)
            User("zhangsan")
        }.await() // GlobalScope.async换成withContext  就不用写await了
    }

    suspend fun fetchAndShowUser() {
        val user = fetchUser() // fetch on IO thread
        println("currentThread-----" + Thread.currentThread().id)
        tvText?.text = user.name // back on UI thread
    }

    fun start() {

        GlobalScope.launch(Dispatchers.Main) { // launch 不返回任何内容  GlobalScope是全局scope activty销毁任务还在运行
            fetchAndShowUser()
        }

        GlobalScope.launch(Dispatchers.Main) {
            val userOne = async(Dispatchers.IO) { fetchFirstUser() }
            val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
            "${userOne.await().name}${userTwo.await().name}".also {
                tvText?.text = it
            } // back on UI thread
        }

        GlobalScope.launch(Dispatchers.Main) {
            val userOne = withContext(Dispatchers.IO) { fetchFirstUser() } // 从async换成witchContext
            val userTwo =
                withContext(Dispatchers.IO) { fetchSecondUser() } // withContext是串行的在同一个io线程 async是在不同的io线程
            "${userOne.name}${userTwo.name}".also {
                tvText?.text = it
            } // back on UI thread
        }

        launch(Dispatchers.Main) { // 使用默认的CoroutineScope   activity一旦销毁 任务就被取消
            val userOne = async(Dispatchers.IO) { fetchFirstUser() }
            val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
            "${userOne.await().name}${userTwo.await().name}".also {
                tvText?.text = it
            } // back on UI thread
        }
    }

    suspend fun fetchFirstUser(): User {
        println("currentThread-----" + Thread.currentThread().id)
        return User("lisi")
    }

    suspend fun fetchSecondUser(): User {
        println("currentThread-----" + Thread.currentThread().id)
        return User("wangwu")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var job: Job

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }

    // let、run、with、also和apply         一般用run 和 apply就够了
    class Person() {
        var name: String? = "ABCD"
        var contactNumber: String = "1234567890"
        var address: String = "xyz"
        fun displayInfo() = print(
            "\n Name: $name\n " +
                    "Contact Number: $contactNumber\n " +
                    "Address: $address"
        )
    }

    // let
    private fun performLetOperation() {
        val person =
            Person().let { it -> // “ let”运算符提供了一个选项来对当前对象执行操作并根据用例返回任何值。 let->it  1.it可以重命名
                "The name of the Person is: ${it.name}}"
            }
        println(person)

        val name = Person().name?.let { // 2.可以很容易判空
            "The name of the Person is: $it}"
        }
        println(name)
        developers.filter { it.name == "zhangsan" }.let { println(it) } // 3链式调用 对结果进行操作
    }

    // run
    private fun performRunOperation() {
        Person().run { // run”运算符可用于初始化对象并返回其结果 run ->this   1.this不可以重命名    2.run也可以很空翼判空
            name = "Asdf"
            contactNumber = "0987654321"
            return@run "The details of the Person is: ${displayInfo()}"
        }
    }

    // with   with和run几乎完全相似  但是一般用run   判空方便一点
    // apply
    private fun performApplyOperation() { // apply 没有return
        val person: Person? = null
        person?.apply {
            name = "asdf"
            contactNumber = "1234"
            address = "wasd"
            displayInfo()
        }
    }

    // also
    private fun performAlsoOperation() {
        val name = Person().also { currentPerson -> // 有助于保持操作链不被破坏  also没有return
            print("Current name is: ${currentPerson.name}\n")
            currentPerson.name = "modifiedName"
        }.run {
            "Modified name is: $name\n"
        }
        print(name)
    }

    // reified
    inline fun <reified T> genericsExample(value: T) { // 传入不同的参数类型   reified搭配inline可以查看具体的类型
        println(value)
        println("Type of T: ${T::class.java}")
    }

    inline fun <reified T> showMessage(marks: Int): T { // 返回不同的参数类型  reified搭配inline可以返回具体的类型
        return when (T::class) {
            Int::class -> marks as T
            String::class -> "Congratulations! you scored more than 90%" as T
            else -> "Please enter valid type" as T
        }
    }

    fun main() {
        genericsExample<String>("Learning Generics!")
        genericsExample<Int>(100)

        val intMarks: Int = showMessage(70) // returning integer value
        val stringMessage: String = showMessage(95) // returning string value
        println("Your marks: $intMarks \nMessage: $stringMessage")
    }

    // 标签 return break continue
    fun label() {
        loop@ for (i in 1..10) { // @创建标签
            // some code goes here
        }

        listOf(1, 2, 3, 4, 5).forEach lit@{ // return 标签
            if (it == 3) return@lit // local return to the caller of the lambda, i.e. the forEach loop
            print("it$it")
        }
        print(" done with explicit label")

        listOf(1, 2, 3, 4, 5).forEach(
            fun(value: Int) { // return
                if (value == 3) return // local return to the caller of the anonymous fun, i.e. the forEach loop
                print(value)
            }
        )
        print(" done with anonymous function")
        loop@ for (i in 1..100) { // loop 1
            for (j in 1..100) { // loop 2
                if (j == 10) break@loop // break 标签
            }
        }
        loop@ for (i in 1..100) { // loop 1
            for (j in 1..100) { // loop 2
                if (j == 10) continue@loop // continue 标签
            }
        }
    }

    // foreach
    fun foreach() {
        var listOfMindOrks = listOf("mindorks.com", "blog.mindorks.com", "afteracademy.com")
        for (items in listOfMindOrks) {
            Log.d(TAG, items)
        }
        listOfMindOrks.forEach { Log.d(TAG, it) }
    }
}

// 对应java的静态方法
class StaticMethod {
    fun getAddress() {
        val companyAddress1 = CompanyUtils1.getCompanyAddress()
        val companyAddress2 = CompanyUtils2.getCompanyAddress()
    }

    fun someDemoMethod() {
        val companyAddress = getCompanyAddress()
    }
}

// 第一种
class CompanyUtils1 {
    companion object {
        fun getCompanyAddress(): String {
            return "MindOrks, G-773, GROUND FLOOR SUNCITY, SECTOR-54 GURUGRAM, HR"
        }
    }
}

// 第二种
object CompanyUtils2 {
    fun getCompanyAddress(): String {
        return "MindOrks, G-773, GROUND FLOOR SUNCITY, SECTOR-54 GURUGRAM, HR"
    }
}

// 第三种
fun getCompanyAddress(): String {
    return "MindOrks, G-773, GROUND FLOOR SUNCITY, SECTOR-54 GURUGRAM, HR"
}

class const_val {
    companion object {
        const val FILE_EXTENSION = ".png"
        val FILENAME: String
            get() = "Img_" + System.currentTimeMillis() + FILE_EXTENSION
    }
}
/* 转换为java源文件
* public final String getFILENAME() {
   return "Img_" + System.currentTimeMillis() + ".png";
}*/

// open    kotlin中所有class都是final的 加上open才能被其他类继承
open class MindOrks(var string: String) {

    open val courseId: Int = 0 // kotlin中所有的变量也是final的  加上open 子类才能覆盖

    open fun courseName() { // kotlin中所有的函数也是final的 加上open 子类才能重写
        println("Course Name")
    }
}

class AndroidPro(string: String) : MindOrks(string) {

    override val courseId: Int = 1

    override fun courseName() {
        println("Android for Professionals")
    }
}

// set get
class Community {
    var name: String = "MindOrks" // 变量是私有的 set get方法是公开的  并且可以省略它们将自动生成

    //        get() = field
//        set(value) {
//            field = value
//        }
    private lateinit var startingDate: String
    var desc: String // set get 方法内进行操作
        get() = "$name $startingDate"
        set(value) {
            val descArray = value.split(" ".toRegex())
            name = descArray[0]
            startingDate = descArray[1]
        }
}

// 单例
object Singleton :
    A() { // 使用object创建单例类 该类可以fun  变量  和init,不允许使用构造方法   可以像普通类一样继承某个类(无构造参数)或者实现别的接口

    init {
        println("Singleton class invoked.")
    }

    var variableName = "I am Var"
    override fun printVarName() {
        println(variableName)
    }

    fun main(args: Array<String>) {
        Singleton.printVarName()
        Singleton.variableName = "New Name"
        var a = A()
    }

    class A {

        init {
            println("Class init method. Singleton variableName property : ${Singleton.variableName}")
            Singleton.printVarName()
        }
    }
}

open class A {

    open fun printVarName() {
        print("I am in class printVarName")
    }

    init {
        println("I am in init of A")
    }
}

// 带参数的单例类  双重检查锁 延迟加载
open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }
        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}

class YourManager private constructor(context: Context) {  //带参数的单例模式标准写法
    init {        //init 在构造方法之后调用
        // do something with context
    }

    init {       //可以有多个init方法  依次执行
        // do something with context
    }

    fun doSomething() {
    }

    companion object singleton :
        SingletonHolder<YourManager, Context>(::YourManager) // 伴生对象   singleton可以省略
}


//pair 和 tripe
fun pairAndTriple() {
    val variable1 = "Declaring String variable"
    val variable2 = 1 // declaring integer value
    val variableName = Pair(variable1, variable2) // using declared variable in Pair class
    println(variableName.first) // will print the value of variable1
    println(variableName.second)

    val (firstVariable, secondVariable) = Pair("Hello", 1)
    println(firstVariable)
    println(secondVariable)

    val (url: String, website: String) = getWebsite()
    println(getWebsite().toString())
    println(getWebsite().toList())


    //triple用法和pair一样

}

//to 作为关键字
fun getWebsite(): Pair<String, String> {
    return "www.mindorks.com" to "the Website is"
}

//map和flatmap
fun map() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val squaredNumbers = mutableListOf<Int>()
    numbers.forEach {
        squaredNumbers.add(it * it)
    }
    //可以简化成
    val simple = numbers.map { it * it }  //map用来转换列表


    val cars = listOf(
        1, 2, 3, 4
    )
    val bikes = listOf(
        10, 9, 8, 7
    )
    val two = listOf<List<Int>>(cars, bikes).flatMap { it }  //flatMap用来将多个列表合并为一个列表
}

//array和list
fun arrayAndList() {
    val coursesArray = arrayOf<String>("Android Beginners", "Android Professionals")
    coursesArray[0] = "Android Basics" // no error  array本质上是可变的
//    coursesArray.add array具有固定大小不可add
    val coursesList = listOf<String>("Android Beginners", "Android Professionals")
//    coursesList[0] = "Android Basics" // error list本质上是不可变的 mutableList是可变的
    val coursesMutableList = mutableListOf<String>("Android Beginners", "Android Professionals")
    coursesMutableList.add("React-Native") //mutableList是可以改变大小的


}




