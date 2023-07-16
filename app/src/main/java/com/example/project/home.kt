package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
    private lateinit var company: RecyclerView
    private lateinit var items:ArrayList<String>
    private lateinit var userlist:ArrayList<company>
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        company=view.findViewById(R.id.recylerview)
        company.layoutManager= GridLayoutManager(activity,2)
        userlist= arrayListOf()
        userlist.add(company(R.drawable.a,"Redmi"))
        userlist.add(company(R.drawable.b,"Samsung"))
        userlist.add(company(R.drawable.ass,"hwawei"))
        userlist.add(company(R.drawable.mic,"Microsoft"))
        userlist.add(company(R.drawable.app,"Apple"))
        userlist.add(company(R.drawable.f,"google"))
        userlist.add(company(R.drawable.real,"realme"))

        var adapter=adapter(userlist);
        company.adapter=adapter

        adapter.random(object :adapter.onItemClickListener{
            override fun onItemClicked(position: Int) {

                if(position==0) {


                    val a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item", "Redmi")
                    startActivity(a)

                }
                if(position==1) {

                    val samsung="Samsung"
                    val a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Samsung")
                    startActivity(a)

                }
                if(position==2) {


                    val a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Hwauei")
                    startActivity(a)

                }
                if(position==3) {


                    var a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Microsoft")
                    startActivity(a)

                }
                if(position==4) {


                    var a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Apple")
                    startActivity(a)

                }
                if(position==5) {


                    var a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Google")
                    startActivity(a)

                }
                if(position==6) {


                    var a = Intent(activity, itemactivity::class.java)
                    a.putExtra("item","Realme")
                    startActivity(a)

                }



            }

        })
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}