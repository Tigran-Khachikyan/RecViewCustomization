package com.task.recviewcustomization

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.recviewcustomization.databinding.ListItemSimpleElementBinding

class DataAdapter : RecyclerView.Adapter<DataAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Double>() {
        override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem        }
    })

    fun submit(data: List<Double>) {
        Log.d("scrol777", "update DataAdapter")
        differ.submitList(data)
    }
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemSimpleElementBinding.bind(itemView)

        fun bind(simpleValue: Double) {
            with(binding) {
                tvSimple.text = simpleValue.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_simple_element, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}