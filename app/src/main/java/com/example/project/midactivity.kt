package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

class midactivity : AppCompatActivity() {
    private lateinit var itemselected:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_midactivity)
        val items= listOf<String>("pdfreader","Samsung","kdkfkr")
        val autoComplete:AutoCompleteTextView=findViewById(R.id.autoComplete)
        val adapter=ArrayAdapter(this,R.layout.textlist,items)
        autoComplete.setAdapter(adapter)
        autoComplete.setOnItemClickListener { parent, view, position, id ->
            itemselected=parent.getItemAtPosition(position).toString()

        }
        var btn=findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            var a=Intent(this,listview::class.java)
            a.putExtra("selected",itemselected)
            startActivity(a)
        }
    }
}