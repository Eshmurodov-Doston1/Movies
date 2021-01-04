package com.example.movies.sharedpreferens

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferens {
    private const val  NAME = "registration"
    private const val  MODE = Context.MODE_PRIVATE
    private lateinit var sharedpreferences: SharedPreferences
    fun  init(context: Context){
        sharedpreferences = context.getSharedPreferences(NAME, MODE)
    }
    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)->Unit){
        var editor = edit()
        operation(editor)
        editor.commit()
    }
    fun clear(){
        text=""
    }
    var text:String?
        get()= sharedpreferences.getString("key1","")
        set(value) = sharedpreferences.edit {
            if (value!=null){
                it.putString("key1",value)
            }
        }
}