package com.task.recviewcustomization

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.task.recviewcustomization.databinding.ListItemDataBinding

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.Holder>() {

    private var dataList: List<Data> = arrayListOf()

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            Log.d(
                "scrol777",
                "areItemsTheSame : oldItem.scrX: " + oldItem.scrX + ", newItem.scrX" + newItem.scrX
            )
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem == newItem
    })

    fun update(data: List<Data>) {
        Log.d("scrol777", "update")
        dataList = data
        differ.submitList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemDataBinding.bind(itemView)

        init {
            with(binding) {
                recDataPut.onScroll()
                recDataCall.onScroll()
                recHeaderCall.onScroll()
                recHeaderPut.onScroll()
            }
        }

        private val headerAdapter: HeaderAdapter = HeaderAdapter()
        private val centralAdapter: CentralAdapter = CentralAdapter()
        private val callsDataAdapter: DataAdapter = DataAdapter()
        private val putsDataAdapter: DataAdapter = DataAdapter()

        fun bind(data: Data) {
            Log.d("scrol777", "bind data.scrX: " + data.scrX)

            with(binding) {
                headerAdapter.submit(data.headerData)
                centralAdapter.submit(data.strikeList)
                callsDataAdapter.submit(data.callData)
                putsDataAdapter.submit(data.putData)

                btnDate.text = data.date

                recHeaderCall.apply {
                    // onScroll()
                    setHasFixedSize(true)
                    // itemAnimator = null
                    //  isNestedScrollingEnabled = false
                    adapter = headerAdapter
                    //layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false){ override fun canScrollHorizontally(): Boolean { return false } }
                }

                recHeaderPut.apply {
                    // onScroll()
                    // itemAnimator = null
                    //  isNestedScrollingEnabled = false

                    setHasFixedSize(true)
                    adapter = headerAdapter

                    //layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false){ override fun canScrollHorizontally(): Boolean { return false } }
                }

                recDataCentral.apply {
                    setHasFixedSize(true)
                    // itemAnimator = null
                    //  isNestedScrollingEnabled = false

                    adapter = centralAdapter

                    // layoutManager = object : LinearLayoutManager(context,RecyclerView.VERTICAL, false){ override fun canScrollVertically(): Boolean { return false } }
                }

                recDataCall.apply {
                    //onScroll()
                    setHasFixedSize(true)
                    // itemAnimator = null
                    /*          isNestedScrollingEnabled = false

                                layoutManager = object :GridLayoutManager(context,3, RecyclerView.HORIZONTAL, false){
                                    override fun canScrollHorizontally(): Boolean { return false }
                                }*/
                    adapter = callsDataAdapter
                }

                recDataPut.apply {
                    // onScroll()
                    setHasFixedSize(true)
                    // isNestedScrollingEnabled = false
                    // itemAnimator = null
                    /*                 layoutManager = object :GridLayoutManager(context,3, RecyclerView.HORIZONTAL, false){
                                         override fun canScrollHorizontally(): Boolean { return false }
                                     }*/
                    adapter = putsDataAdapter
                }

                data.scrX?.let {
                    Log.d("scrol777", "in BINDING data.scrX: " + it)
                    recDataCall.scrollBy(it, 0)
                    recDataPut.scrollBy(it, 0)
                    recHeaderCall.scrollBy(it, 0)
                    recHeaderPut.scrollBy(it, 0)
                }
            }
        }


        private fun RecyclerView.onScroll() {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // binding.recDataPut.scrollBy(dx,dy)
              /*      binding.recDataCall.scrollBy(dx, dy)
                    binding.recHeaderPut.scrollBy(dx, dy)
                    binding.recHeaderCall.scrollBy(dx, dy)*/
                }
            })
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_data, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}