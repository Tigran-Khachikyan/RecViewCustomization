package com.task.recviewcustomization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task.recviewcustomization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var parentAdapter: ParentAdapter

    private val headers : List<String> = listOf("Bid","Ask","Pin","Puk", "Pu1","Pu2")
    private val dataList : List<Data> = listOf(
        Data(
            date = "12 Aug 21",
            callData = listOf(25.30,0.18,18.99,24.77,25.30,0.18, 25.30,0.18,18.99,24.77,25.30,0.18,18.99,24.77,25.30,0.18,25.30,0.18),
            putData = listOf(11.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44,25.30,0.18,25.30,0.18,25.30,0.18),
            strikeList = listOf(99.99,88.88,77.77),headers
        ),
        Data(
            date = "12 Aug 21",
            callData = listOf(25.30,0.18,18.99,24.77, 25.30,0.18,18.99,24.77,25.30,0.18,18.99,24.77,25.30,0.18,25.30,0.18,25.30,0.18),
            putData = listOf(11.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44,25.30,0.18,25.30,0.18,25.30,0.18),
            strikeList = listOf(99.99,88.88,77.77), headers
        ),
        Data(
            date = "12 Aug 21",
            callData = listOf(25.30,0.18,18.99,24.77, 25.30,0.18,18.99,24.77,25.30,0.18,18.99,24.77,25.30,0.18,25.30,0.18,25.30,0.18),
            putData = listOf(11.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44,25.30,0.18,25.30,0.18,25.30,0.18),
            strikeList = listOf(99.99,88.88,77.77), headers
        ),
        Data(
            date = "12 Aug 21",
            callData = listOf(25.30,0.18,18.99,24.77, 25.30,0.18,18.99,24.77,25.30,0.18,18.99,24.77,25.30,0.18,25.30,0.18,25.30,0.18),
            putData = listOf(11.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44, 1.11,22.22,33.33,44.44,25.30,0.18,25.30,0.18,25.30,0.18),
            strikeList = listOf(99.99,88.88,77.77), headers
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView(){
        parentAdapter = ParentAdapter()
        parentAdapter.update(dataList)
        binding.recParent.apply {
            setHasFixedSize(true)
            adapter = parentAdapter
        }
    }


}