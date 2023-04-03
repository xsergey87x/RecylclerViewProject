package com.example.testrecycleview

import android.app.AlertDialog
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

class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listItem = arrayListOf<String>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val buttonAdd = findViewById<Button>(R.id.addItemButton)


        val itemAdapter = RecyclerAdapter(this, listItem,this)

        listItem.addAll(listOf("First_Item", "Second_Item"))          /// add some template to recycleView


        buttonAdd.setOnClickListener {
            listItem.add("123454")
            recyclerView.adapter = itemAdapter
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter


        val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.BLACK, Color.YELLOW)
        )
        drawable.setSize(10, 5)

        DividerItemDecoration(this,(recyclerView.layoutManager as LinearLayoutManager).orientation).apply {
            setDrawable(drawable);
            recyclerView.addItemDecoration(this)
        }
    }

    override fun onCellClickListener(data: String) {
        Toast.makeText(this,"Cell clicked ++++++++ $data", Toast.LENGTH_SHORT).show()
        createDialog()
    }

    fun createDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Item")
        builder.setMessage("Do you want to delete item?")

        builder.setNeutralButton("Info") {
            dialogInterface, i ->
        }
        builder.show()
    }
}