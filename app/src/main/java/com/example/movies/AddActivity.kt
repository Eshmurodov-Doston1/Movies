package com.example.movies

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movies.models.MovieClass
import com.example.movies.sharedpreferens.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieClass>
    lateinit var mySharedPreferences: SharedPreferences
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        MySharedPreferens.init(this)

        save_btn.setOnClickListener {
            var name = edite_name.text.toString()
            var authors = authors.text.toString()
            var movie = movie.text.toString()
            var date = date.text.toString()

            if (name.isNullOrBlank() && authors.isNullOrBlank() && movie.isNullOrBlank()) {
                Toast.makeText(this, "Malumotlar mavjud emas", Toast.LENGTH_SHORT).show()
            }else if(name.isNullOrBlank()){
                Toast.makeText(this, "Name kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if(authors.isNullOrBlank()){
                Toast.makeText(this, "Authorslar mavjud kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if(movie.isNullOrBlank()){
            Toast.makeText(this, "About mavjud  kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if (name.isNotEmpty() && authors.isNotEmpty() && movie.isNotEmpty() && date.isNotEmpty()) {
                    list = ArrayList()
                    gson = Gson()
                    var str = MySharedPreferens.text
                    if (str!!.isNotEmpty()) {
                        var type = object : TypeToken<ArrayList<MovieClass>>() {}.type
                        list.addAll(gson.fromJson(str, type))
                    }
                    var movieClass = MovieClass(name, authors, movie, date)
                    var isHave = false
                    for (i in list.indices) {
                        if (list[i].name!! == name) {
                            isHave = true
                        }
                    }
                    if (!isHave) {
                        list.add(movieClass)
                        MySharedPreferens.text = gson.toJson(list)

                        finish()
                    } else {
                        Toast.makeText(this, "Bunday malumot mavjud", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}