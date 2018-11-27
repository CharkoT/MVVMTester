package com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charko.tester.mvvmtester.R

class KotlinImageViewFragment : Fragment() {

    companion object {
        fun newInstance() = KotlinImageViewFragment()
    }

    private lateinit var viewModel: KotlinImageViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.kotlin_image_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(KotlinImageViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
