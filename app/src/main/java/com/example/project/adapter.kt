package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private var userlist:ArrayList<company>):RecyclerView.Adapter<adapter.MyViewHolder>() {
    private lateinit var mlistener:onItemClickListener
    interface onItemClickListener{
        fun onItemClicked(position: Int)
    }
    fun random(listener:onItemClickListener){
        mlistener=listener
    }

    class MyViewHolder(itemView:View,listener:onItemClickListener):RecyclerView.ViewHolder(itemView) {
        var name=itemView.findViewById<TextView>(R.id.companyname)
        var image=itemView.findViewById<ImageView>(R.id.companyimage)
        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list,parent,false)
        return MyViewHolder(itemView,mlistener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text=userlist[position].name
        holder.image.setImageResource(userlist[position].image)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

}