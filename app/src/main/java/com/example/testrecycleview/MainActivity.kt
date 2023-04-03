package com.example.testrecycleview

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listItem = arrayListOf<String>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val buttonAdd = findViewById<Button>(R.id.addItemButton)


        val itemAdapter = RecyclerAdapter(listItem,this)

        listItem.addAll(listOf("First_Item", "Second_Item"))          /// add some template to recycleView


        buttonAdd.setOnClickListener {
            listItem.add("123454")
            recyclerView.adapter = itemAdapter
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter

       /// itemAdapter.setOnClickListener(object : RecyclerAdapter.OnClickListener {
//
//            override fun onClick(position: Int, model: String) {
//                val text = "Result  *********** $position"
//                val duration = Toast.LENGTH_LONG
//
//                print("RESULT *********************************  $position")
//                val toast = Toast.makeText(applicationContext, text, duration)
//
//            }
//        })

        val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.BLACK, Color.YELLOW)
        )
        drawable.setSize(10, 5)

        DividerItemDecoration(this,(recyclerView.layoutManager as LinearLayoutManager).orientation).apply {
            setDrawable(drawable);
            recyclerView.addItemDecoration(this)
        }


    }


    override fun onItemClick(position: Int) {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "I did it"
        Toast.makeText(this,"Cell clicked : $position", Toast.LENGTH_SHORT).show()
        Log.d("tag", "Item clicked: $position")
    }
}