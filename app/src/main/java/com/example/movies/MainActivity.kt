package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.adapter.RvAdapter
import com.example.movies.models.MovieClass
import com.example.movies.sharedpreferens.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_item.*

class MainActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MovieClass>
    lateinit var rvAdapter: RvAdapter
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MySharedPreferens.init(this)
        loadAdapter()
        img_view.setOnClickListener {
            var intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        loadAdapter()
        MySharedPreferens.init(this)
        rvAdapter = RvAdapter(list, object : RvAdapter.MyItemClickListiner {
            override fun itemClickListener(movieClass: MovieClass, position: Int) {
               var intent = Intent(this@MainActivity,IformationActivity::class.java)
                intent.putExtra("key",list[position])
                startActivity(intent)
            }

            override fun editClickListener(movieClass: MovieClass, position: Int) {
                var intent = Intent(this@MainActivity, EditeActivity::class.java)
                intent.putExtra("position",list[position])
                rvAdapter.notifyItemChanged(position)
                startActivity(intent)
            }
            override fun deletClickListener(movieClass: MovieClass, position: Int) {
             list.remove(movieClass)
                rvAdapter.notifyItemRemoved(position)
                rvAdapter.notifyItemRangeChanged(position,list.size)
                MySharedPreferens.clear()
                if (MySharedPreferens.text!!.isEmpty()){
                    MySharedPreferens.text = gson.toJson(list)
                }

            }

        })
        rv.adapter = rvAdapter
    }
    private fun loadAdapter() {
        list = ArrayList()
        var str = MySharedPreferens.text
        gson = Gson()
        if (str!!.isNotEmpty()){
            var type = object:TypeToken<ArrayList<MovieClass>>(){}.type
            list.addAll(gson.fromJson(str,type))
        }
    }
}