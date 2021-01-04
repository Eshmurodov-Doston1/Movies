package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.models.MovieClass
import kotlinx.android.synthetic.main.movie_item.view.*

class RvAdapter(var list: List<MovieClass>,var myItemClickListiner: MyItemClickListiner):RecyclerView.Adapter<RvAdapter.MyVivoHolder>() {
    inner class MyVivoHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun oBind( movieClass: MovieClass,position: Int){
          itemView.avengers.text = movieClass.name
          itemView.text_names.text = movieClass.authors
            itemView.date.text = movieClass.date
            itemView.setOnClickListener {
                myItemClickListiner.itemClickListener(movieClass,position)
            }
            itemView.edite.setOnClickListener {
                 myItemClickListiner.editClickListener(movieClass,position)
            }
            itemView.delete.setOnClickListener {
                myItemClickListiner.deletClickListener(movieClass,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVivoHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
       var myVivoHolder = MyVivoHolder(itemView)
        return myVivoHolder
    }

    override fun onBindViewHolder(holder: MyVivoHolder, position: Int) {
      var movieClass = list[position]
        holder.oBind(movieClass,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface MyItemClickListiner{
        fun itemClickListener(movieClass: MovieClass,position: Int)
        fun editClickListener(movieClass: MovieClass,position: Int)
        fun deletClickListener(movieClass: MovieClass,position: Int)
    }
}