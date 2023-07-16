package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class menu : AppCompatActivity() {
    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var drawerlayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        drawerlayout = findViewById(R.id.drawer)
        val navview:NavigationView=findViewById(R.id.navview)
        toggle= ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replacefragment(home(),"Home")
        navview.setNavigationItemSelectedListener {
            it.isChecked=true
            when(it.itemId){
                R.id.home->replacefragment(home(),it.title.toString())
                R.id.aboutus-> replacefragment(aboutus(),it.title.toString())
                R.id.contact-> replacefragment(contact(),it.title.toString())
                R.id.upload->{
                    var a= Intent(applicationContext,upload::class.java)
                    startActivity(a)
                }
            }
            true

        }

    }
    private fun replacefragment(fragment:Fragment,title:String){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        drawerlayout.closeDrawers()
        setTitle(title)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}