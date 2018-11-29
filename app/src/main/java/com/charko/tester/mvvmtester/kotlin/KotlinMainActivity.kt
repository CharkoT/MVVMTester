package com.charko.tester.mvvmtester.kotlin

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
        adapter.addClickListener { positioin ->
            val item = items[positioin]
            var intent = Intent(this, KotlinImageViewActivity::class.java)
            intent.putExtra("picture", item)
            intent.putExtra("position", positioin)

            startActivityForResult(intent, 100)
        }

        rvImage.layoutManager = LinearLayoutManager(this)
        rvImage.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(KotlinImageViewViewModel::class.java)
        viewModel.getPictures(applicationContext).observe(this, Observer<List<Picture>> { pictrues ->
            Log.e(">>>>>>>>>>>>>", ">>>>>> Kotlin update Pictures!!")
            items = pictrues as ArrayList<Picture>
            adapter.items = items

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val position = data?.getIntExtra("position", -1)
            val picture = data?.getParcelableExtra<Picture>("picture")

            position?.let {
                if (position!! >= 0)
                    viewModel.update(position!!, picture!!)

            }
        }
    }
}
