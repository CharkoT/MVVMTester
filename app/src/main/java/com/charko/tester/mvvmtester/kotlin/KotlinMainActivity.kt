package com.charko.tester.mvvmtester.kotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.charko.tester.mvvmtester.R
import com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview.KotlinImageViewViewModel
import com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview.adapter.KImageViewHolderAdapter
import com.charko.tester.mvvmtester.simplemodel.Picture

class KotlinMainActivity : AppCompatActivity() {

    lateinit var rvImage: RecyclerView
    var items = ArrayList<Picture>()
    lateinit var adapter: KImageViewHolderAdapter

    lateinit var viewModel: KotlinImageViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        rvImage = findViewById(R.id.picture_list_rv)
        adapter = KImageViewHolderAdapter(this, items)

        rvImage.layoutManager = LinearLayoutManager(this)
        rvImage.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(KotlinImageViewViewModel::class.java)
        viewModel.getPictures(applicationContext).observe(this, Observer<List<Picture>> { pictrues ->
            Log.e(">>>>>>>>>>>>>", ">>>>>> Kotlin update Pictures!!")
            adapter.items = pictrues as ArrayList<Picture>

        })


    }
}
