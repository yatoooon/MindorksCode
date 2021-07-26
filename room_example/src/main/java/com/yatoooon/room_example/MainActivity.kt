package com.yatoooon.room_example

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var chapterDatabase: ChapterDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chapterDatabase = ChapterDatabase.getDatabase(this)!!
        btnSave.setOnClickListener {
            if (etEnterText.text.toString().isNotEmpty()) {
                val chapterObj = Chapter(etEnterText.text.toString())
                InsertTask(this, chapterObj).execute()
            }
        }
        btnDisplay.setOnClickListener {
            GetDataFromDb(this).execute()
        }
    }
    private class InsertTask(var context: MainActivity, var chapter: Chapter) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.chapterDatabase!!.chapterDao().insert(chapter)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show()
            }
        }
    }
    private class GetDataFromDb(var context: MainActivity) : AsyncTask<Void, Void, List<Chapter>>() {
        override fun doInBackground(vararg params: Void?): List<Chapter> {
            return context.chapterDatabase!!.chapterDao().getAllChapter()
        }
        override fun onPostExecute(chapterList: List<Chapter>?) {
            if (chapterList!!.isNotEmpty()) {
                for (element in chapterList) {
                    context.tvDatafromDb.append(element.chapterName)
                }
            }
        }
    }
}

@Entity(tableName = "MindOrksDb")
data class Chapter(
    @PrimaryKey
    @ColumnInfo(name = "chapterName") val chapterName: String
)

@Dao
interface ChapterDao {
    @Insert
    fun insert(chapter: Chapter)
    @Query("SELECT * FROM MindOrksDb ORDER BY chapterName ASC")
    fun getAllChapter(): List<Chapter>
}

@Database(entities = [Chapter::class], version = 1,exportSchema=false)
abstract class ChapterDatabase : RoomDatabase() {
    abstract fun chapterDao(): ChapterDao
    companion object {
        private var INSTANCE: ChapterDatabase? = null
        fun getDatabase(context: Context): ChapterDatabase? {
            if (INSTANCE == null) {
                synchronized(ChapterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ChapterDatabase::class.java, "chapter.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}