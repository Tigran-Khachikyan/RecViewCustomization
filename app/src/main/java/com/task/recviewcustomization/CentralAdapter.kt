package com.task.recviewcustomization

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.recviewcustomization.databinding.ListItemCentralElementBinding

class CentralAdapter : RecyclerView.Adapter<CentralAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Double>() {
        override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem        }
    })

    fun submit(data: List<Double>) {
        Log.d("scrol777", "update CentralAdapter")
        differ.submitList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemCentralElementBinding.bind(itemView)

        fun bind(strikeData: Double){
            with(binding){
                tvCentralElement.text = strikeData.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_central_element, parent, false)
        return Holder(view)    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}