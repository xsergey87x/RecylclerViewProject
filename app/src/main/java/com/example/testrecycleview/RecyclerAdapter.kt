package com.example.testrecycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    val context: Context,
    private val items: List<String>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycle_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        items[position].apply {
            holder.itemText.text = items[position]
            holder.titleText.text = position.toString()
        }
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(items[position], position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemText: TextView = view.findViewById(R.id.itemTextView)
        val titleText: TextView = view.findViewById(R.id.titleTextView)

    }
}