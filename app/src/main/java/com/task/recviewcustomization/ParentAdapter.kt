package com.task.recviewcustomization

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.task.recviewcustomization.databinding.ListItemDataBinding
import android.widget.HorizontalScrollView


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

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
            oldItem == newItem
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
                nestedPut.onScroll()
                nestedCall.onScroll()
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

                /*  nestedCall.setOnScrollChangeListener(
                      NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                          Log.d("scrol777", "nestedCall.setOnScrollChangeListener - x: " + scrollX)
                          this@ParentAdapter.scrollX = scrollX
                          notifyDataSetChanged()
                      }
                  )

                  nestedPut.setOnScrollChangeListener(
                      NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                          Log.d("scrol777", "nestedPut.setOnScrollChangeListener - x: " + scrollX)
                          this@ParentAdapter.scrollX = scrollX
                          notifyDataSetChanged()
                      }
                  )*/

                data.scrX?.let {
                    Log.d("scrol777", "in BINDING data.scrX: " + it)
                      nestedCall.scrollTo(it, 0)
                      nestedPut.scrollTo(it, 0)
                    /*    binding.recHeaderPut.smoothScrollToPosition(2)
                        binding.recHeaderCall.smoothScrollToPosition(2)
                        binding.recDataCall.smoothScrollToPosition(2)
                        binding.recDataPut.smoothScrollToPosition(2)*/

            /*        recHeaderPut.scrollTo(it, 0)
                    recHeaderCall.scrollTo(it, 0)
                    recDataCall.scrollTo(it, 0)
                    recDataPut.scrollTo(it, 0)*/
                }
            }
        }

        /*    private fun RecyclerView.onScroll() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                        Log.d(
                            "scrol777",
                            "nestedCall.setOnScrollChangeListener - x: " + scrollX + ": old - " + oldScrollX
                        )
                        if (scrollX - oldScrollX != 0) {
                            data[0].scrX = scrollX
                            update(data)
                        }
                    }
                }
            }*/


        private fun HorizontalScrollView.onScroll() {
            /*viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener {
                //val scrollY: Int = rootScrollView.getScrollY() // For ScrollView
                val scrollX: Int = scrollX // For HorizontalScrollView
                val newData = ArrayList<Data>(differ.currentList)
                val d = newData.map {
                    it.copy(scrX = scrollX)
                }
                update(d)
                // DO SOMETHING WITH THE SCROLL COORDINATES
            })*/

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    //if (oldScrollX - scrollX != 0) {
                    if (scrollX != 0) {
                        Log.d(
                            "scrol777", "HorizScrol - x: " + scrollX + ": old - " + oldScrollX
                        )
                        val newData = ArrayList<Data>(dataList)
                        val copied = newData.map {
                            it.copy(scrX = scrollX)
                        }
                        update(copied)
                    }
                }
            }
        }
    }

    private fun RecyclerView.onScroll() {
        /*   addOnScrollListener(object : RecyclerView.OnScrollListener(){

               override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                   super.onScrollStateChanged(recyclerView, newState)
               }

               override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                   super.onScrolled(recyclerView, dx, dy)

                  // binding.recDataPut.scrollBy(dx,dy)
                   binding.recDataCall.scrollBy(dx,dy)
                   binding.recHeaderPut.scrollBy(dx,dy)
                   binding.recHeaderCall.scrollBy(dx,dy)
               }
           })*/


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (oldScrollX - scrollX != 0) {
                    Log.d(
                        "scrol777",
                        "nestedCall.setOnScrollChangeListener - x: " + scrollX + ": old - " + oldScrollX
                    )

                    differ.currentList.forEach {
                        Log.d(
                            "scrol777",
                            "differ.currentList scrX: " + it.scrX
                        )
                    }
                    val newData = ArrayList<Data>(differ.currentList)
                    val copied = newData.map { it.copy(scrX = -oldScrollX) }
                    copied.forEach { Log.d("scrol777", " newData scrX: " + it.scrX) }
                    update(copied)
                }
            }
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