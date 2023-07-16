package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var company:RecyclerView
    private lateinit var items:ArrayList<String>
    private lateinit var userlist:ArrayList<company>
    private lateinit var item:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        company=findViewById(R.id.recylerview)
        company.layoutManager=GridLayoutManager(this,2)
        userlist= arrayListOf()
        userlist.add(company(R.drawable.a,"Redmi"))
        userlist.add(company(R.drawable.b,"Samsung"))
        userlist.add(company(R.drawable.b,"hw"))
        userlist.add(company(R.drawable.f,"Microsoft"))
        userlist.add(company(R.drawable.a,"Apple"))
        userlist.add(company(R.drawable.f,"google"))
        userlist.add(company(R.drawable.a,"realme"))

        var adapter=adapter(userlist);
        company.adapter=adapter


        adapter.random(object :adapter.onItemClickListener{
            override fun onItemClicked(position: Int) {


                    if(position==0) {


                        val a = Intent(this@MainActivity, itemactivity::class.java)
                        a.putExtra("item", "Redmi")
                        startActivity(a)

                    }
                if(position==1) {

                    val samsung="Samsung"
                    val a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Samsung")
                    startActivity(a)

                }
                if(position==2) {


                    val a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Hwauei")
                    startActivity(a)

                }
                if(position==3) {


                    var a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Microsoft")
                    startActivity(a)

                }
                if(position==4) {


                    var a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Apple")
                    startActivity(a)

                }
                if(position==5) {


                    var a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Google")
                    startActivity(a)

                }
                if(position==6) {


                    var a = Intent(this@MainActivity, itemactivity::class.java)
                    a.putExtra("item","Realme")
                    startActivity(a)

                }

                }

        })



    }
}