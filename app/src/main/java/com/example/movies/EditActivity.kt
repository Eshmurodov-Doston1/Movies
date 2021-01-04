package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.models.MovieClass
import com.example.movies.sharedpreferens.MySharedPreferens

class EditActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        MySharedPreferens.init(this)
    }
}