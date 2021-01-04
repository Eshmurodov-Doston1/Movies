package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.models.MovieClass
import kotlinx.android.synthetic.main.activity_iformation.*

class IformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iformation)
        val information = intent.getSerializableExtra("key") as MovieClass
        name_use.text = information.name
        name_1.text = "Movie name: ${information.name}"
        authors1.text ="Movie authors: ${information.authors}"
        about1.text = "About movie: ${information.movie}"
        date1.text = "Date: ${information.date}"
    }
}