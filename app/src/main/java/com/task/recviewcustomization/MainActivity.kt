package com.task.recviewcustomization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task.recviewcustomization.databinding.ActivityMainBinding
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var parentAdapter: ParentAdapter
    private val headers: List<String> = listOf("Bid", "Ask", "Pin", "Puk", "Mok", "Mil")

    private val dataRowList: List<Double> by lazy {
        val res = arrayListOf<Double>()
        for (i in 1..60) {
            res.add(i.toDouble())
        }
        res
    }

    private val strikeDataList: List<Double> by lazy {
        val res = arrayListOf<Double>()
        for (i in 1.. dataRowList.size/headers.size) {
            res.add(i.toDouble())
        }
        res
    }

    private val dataList: List<Data> = listOf(
        Data(
            date = "12 Aug 21",
            callData = dataRowList,
            putData = dataRowList,
            strikeList = strikeDataList,
            headerData = headers
        ),
        Data(
            date = "12 Aug 21",
            callData = dataRowList,
            putData = dataRowList,
            strikeList = strikeDataList,
            headerData = headers
        ),
        Data(
            date = "12 Aug 21",
            callData = dataRowList,
            putData = dataRowList,
            strikeList = strikeDataList,
            headerData = headers
        ),
        Data(
            date = "12 Aug 21",
            callData = dataRowList,
            putData = dataRowList,
            strikeList = strikeDataList,
            headerData = headers
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {

        parentAdapter = ParentAdapter()
        parentAdapter.update(dataList)
        binding.recParent.apply {
            setHasFixedSize(true)
            adapter = parentAdapter
        }

    }


}