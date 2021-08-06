package com.task.recviewcustomization

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.recviewcustomization.databinding.ListItemSimpleHeaderBinding

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem        }
    })

    fun submit(data: List<String>) {
        Log.d("scrol777", "update HeaderAdapter")
        differ.submitList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemSimpleHeaderBinding.bind(itemView)

        fun bind(header: String){
            with(binding){
                tvHeader.text = header
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_simple_header, parent, false)
        return Holder(view)    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}