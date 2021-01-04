package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movies.adapter.RvAdapter
import com.example.movies.models.MovieClass
import com.example.movies.sharedpreferens.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_edite.*
import kotlinx.android.synthetic.main.movie_item.*

class EditeActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edite)
        MySharedPreferens.init(this)
        var list1 = ArrayList<MovieClass>()
        var position = intent.getSerializableExtra("position") as MovieClass
        edite_name1.setText(position.name)
        authors1.setText(position.authors)
        movie1.setText(position.movie)
        date1.setText(position.date)

        edite_btn.setOnClickListener {
            var gson = Gson()
            var edite_name = edite_name1.text.toString()
            var edite_authors = authors1.text.toString()
            var movie1 = movie1.text.toString()
            var date1 = date1.text.toString()
            var text = MySharedPreferens.text
            if (text!!.isNotEmpty()) {
                var type = object : TypeToken<ArrayList<MovieClass>>() {}.type
                list1.addAll(gson.fromJson(text, type))
            }
            if (edite_name.isEmpty()&& edite_authors.isNotEmpty() && movie1.isNotEmpty() && date1.isNotEmpty()){
                Toast.makeText(this, "Name mavju edmas", Toast.LENGTH_SHORT).show()
                println("Yuq")
            }else if (edite_name.isNotEmpty() && edite_authors.isNotEmpty() && movie1.isNotEmpty() && date1.isNotEmpty()) {
                    list1.removeAll(listOf(position))
                    MySharedPreferens.clear()
                    if (MySharedPreferens.text!!.isEmpty()) {
                        list1.add(MovieClass(edite_name, edite_authors, movie1, date1))
                        MySharedPreferens.text = gson.toJson(list1)
                    }
                    Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                    finish()
            }
            //The data is incomplete
        }
    }
}