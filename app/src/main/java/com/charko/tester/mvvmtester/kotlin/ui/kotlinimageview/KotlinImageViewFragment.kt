package com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.charko.tester.mvvmtester.R
import com.charko.tester.mvvmtester.simplemodel.Picture

class KotlinImageViewFragment : Fragment() {

    lateinit var ivPic: ImageView
    lateinit var tvUri: TextView
    lateinit var tvFilename: TextView
    lateinit var tvLoc: TextView
    lateinit var etDesc: EditText

    lateinit var btnOK: Button
    private var savePosition: Int = 0
    private var picture: Picture? = null

    companion object {
        fun newInstance() = KotlinImageViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        var rootView = inflater.inflate(R.layout.kotlin_image_view_fragment, container, false)
        val intent = activity!!.intent
        picture = intent.getParcelableExtra<Picture>("picture")
        savePosition = intent.getIntExtra("position", -1)

        ivPic = rootView.findViewById(R.id.image_iv)
        tvUri = rootView.findViewById(R.id.uri_tv)
        tvFilename = rootView.findViewById(R.id.filename_tv)
        tvLoc = rootView.findViewById(R.id.loc_tv)
        etDesc = rootView.findViewById(R.id.etc_et)

        btnOK = rootView.findViewById(R.id.ok_btn)

        btnOK.setOnClickListener {

            picture?.let {
                var intent1 = Intent()
                var desc = etDesc.text.toString().trim { it <= ' ' }

                picture?.setDesc(desc)
                intent1.putExtra("position", savePosition)
                intent1.putExtra("picture", picture!!)
                activity!!.setResult(Activity.RESULT_OK, intent1)
                activity!!.finish()
            }

        }

        Glide.with(context!!).load(picture?.getUri()).into(ivPic)

        tvUri.setText(picture?.getUri())
        tvFilename.setText(picture?.getFilename())
        tvLoc.setText(picture?.getLocation())
        etDesc.setText(picture?.getDesc())

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel


    }

}
