package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class homeactivity : AppCompatActivity() {
    private lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeactivity2)
        val drawerlayout:DrawerLayout=findViewById(R.id.drawer)
        val navview:NavigationView=findViewById(R.id.navview)
        toggle= ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> Toast.makeText(applicationContext, "you have clicked home", Toast.LENGTH_SHORT).show()
                R.id.aboutus-> Toast.makeText(applicationContext, "you have clicked aboutus", Toast.LENGTH_SHORT).show()
               R.id.contact-> Toast.makeText(applicationContext, "you have clicked contact", Toast.LENGTH_SHORT).show()
            }
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}