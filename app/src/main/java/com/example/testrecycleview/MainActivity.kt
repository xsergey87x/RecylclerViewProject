package com.example.testrecycleview

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {

    val listItem = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val listItem = arrayListOf<String>()
        val textInputItemView = findViewById<TextView>(R.id.itemNameInput)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val buttonAdd = findViewById<Button>(R.id.addItemButton)
        val itemAdapter = RecyclerAdapter(this, listItem,this)

        listItem.addAll(listOf("First_Item", "Second_Item"))          /// add some template to recycleView


        buttonAdd.setOnClickListener {
            if (textInputItemView.text.isNotEmpty())
            {
                listItem.add("${textInputItemView.text}")
                recyclerView.adapter = itemAdapter
            }

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

    override fun onCellClickListener(data: String, position: Int) {
        //Toast.makeText(this,"Cell clicked ++++++++ $data", Toast.LENGTH_SHORT).show()
        createDialog(data, position)
    }

    private fun createDialog(itemName: String, position: Int)
    {
        val dialogBinding = layoutInflater.inflate(R.layout.custom_dialog,null)
        val customDialog = Dialog(this)

        customDialog.setContentView(dialogBinding)
        customDialog.setCancelable(true)
        customDialog.show()

        val textItem = dialogBinding.findViewById<TextView>(R.id.textViewItem)
        val okButton = dialogBinding.findViewById<Button>(R.id.buttonOk)
        val cancelButton = dialogBinding.findViewById<Button>(R.id.buttonCancel)

        textItem.text = "Item: $itemName, position: $position"

        okButton.setOnClickListener {
            deleteItem(position)
            customDialog.dismiss()
        }

        cancelButton.setOnClickListener {
            customDialog.dismiss()
        }

//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delete Item")
//        builder.setMessage("Do you want to delete item?")
//
//        builder.setNeutralButton("Info") {
//            dialogInterface, i ->
//        }
//        builder.show()
    }

    private fun deleteItem(position: Int) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        listItem.removeAt(position)
        val itemAdapter = RecyclerAdapter(this, listItem,this)
        recyclerView.adapter = itemAdapter
    }
}