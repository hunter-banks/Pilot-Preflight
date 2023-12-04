package com.appdev.pilot_preflight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MyAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>(), Filterable {

    private var filteredList: List<String> = dataList

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.result_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = filteredList[position]
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString().lowercase(Locale.ROOT).trim()

                filteredList = if (charString.isEmpty()) {
                    dataList
                } else {
                    dataList.filter { it.lowercase(Locale.ROOT).contains(charString) }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<String>
                notifyDataSetChanged()
            }
        }
    }
}
