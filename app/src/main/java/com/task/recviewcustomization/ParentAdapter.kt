package com.task.recviewcustomization

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.task.recviewcustomization.databinding.ListItemDataBinding

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.Holder>() {

    private var dataList: List<Data> = arrayListOf()
    private var scrollOffset = 0

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem == newItem
    })

    fun update(data: List<Data>) {
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
                val columnCount = data.headerData.size
                val spanCount = data.callData.size / columnCount
                val call = convert(data.callData, columnCount)
                val put = convert(data.callData, columnCount)
                call.forEach {
                    Log.d("kksa555", "k: " + it)
                }
                headerAdapter.submit(data.headerData)
                centralAdapter.submit(data.strikeList)
                callsDataAdapter.submit(call)
                putsDataAdapter.submit(put)

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
                    layoutManager = GridLayoutManager(context, spanCount, RecyclerView.HORIZONTAL, false)
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
                    layoutManager = GridLayoutManager(context, spanCount, RecyclerView.HORIZONTAL, false)
                    // isNestedScrollingEnabled = false
                    // itemAnimator = null
                    /*                 layoutManager = object :GridLayoutManager(context,3, RecyclerView.HORIZONTAL, false){
                                         override fun canScrollHorizontally(): Boolean { return false }
                                     }*/
                    adapter = putsDataAdapter
                }

                data.scrX?.let {
                    Log.d("scrol777", "in BINDING data.scrX: " + it)
                    recDataCall.scrollBy(scrollOffset, 0)
                    recDataPut.scrollBy(scrollOffset, 0)
                    recHeaderCall.scrollBy(scrollOffset, 0)
                    recHeaderPut.scrollBy(scrollOffset, 0)
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



                    if (dx == 0) {
                        scrollOffset = 0;
                    } else {
                        scrollOffset += dx;
                    }

                    /*      with(binding) {
                              recDataCall.offsetChildrenHorizontal(dx)
                              recDataPut.offsetChildrenHorizontal(dx)
                              recHeaderCall.offsetChildrenHorizontal(dx)
                              recHeaderPut.offsetChildrenHorizontal(dx)
                          }*/

                    Log.d("scrol777", "HorizScrol - x: " + dx)
                    val newData = ArrayList<Data>(dataList)
                    val copied = newData.map {
                        it.copy(scrX = scrollOffset)
                    }
                    update(copied)
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