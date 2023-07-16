package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class itemactivity : AppCompatActivity() {
    private lateinit var item:RecyclerView
    private lateinit var itemlist:ArrayList<company>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemactivity)
        val intent= intent

        item=findViewById(R.id.random)
        item.layoutManager=GridLayoutManager(this,2)
        itemlist= arrayListOf()
        itemlist.add(company(R.drawable.ic_baseline_phone_android_24,"phone"))

        itemlist.add(company(R.drawable.ic_baseline_camera_alt_24,"Camera"))
        itemlist.add(company(R.drawable.ic_baseline_tv_24,"TV"))
        var phone="phone"
        var camera="camera"
        var tv="tv"
        var adapter=adapter(itemlist)
        item.adapter=adapter
        adapter.random(object :adapter.onItemClickListener{
            override fun onItemClicked(position: Int) {
                val select=intent.getStringExtra("item")

                if(position==0){
                    val a=Intent(applicationContext,listview::class.java)
                   val combine=select+phone
                    combine.trim()
                    a.putExtra("selected",combine)
                    startActivity(a)
                }
                if(position==1){
                    val a=Intent(applicationContext,listview::class.java)
                  val combine=select+camera
                    combine.trim()
                    a.putExtra("selected",combine)
                    startActivity(a)
                }
                if(position==2){
                    val a=Intent(applicationContext,listview::class.java)
                    val combine=select+tv
                    combine.trim()
                    a.putExtra("selected",combine)
                    startActivity(a)
                }




            }

        })
    }
}