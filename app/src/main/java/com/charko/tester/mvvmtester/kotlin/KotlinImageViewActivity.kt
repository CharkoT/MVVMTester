package com.charko.tester.mvvmtester.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.charko.tester.mvvmtester.R
import com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview.KotlinImageViewFragment

class KotlinImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_image_view_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, KotlinImageViewFragment.newInstance())
                    .commitNow()
        }

    }

}
