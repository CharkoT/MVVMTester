package com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.charko.tester.mvvmtester.R
import com.charko.tester.mvvmtester.simplemodel.Picture

class KImageViewHolderAdapter : RecyclerView.Adapter<KImageViewHolderAdapter.KImageViewHolder> {
    val context: Context
    var items: ArrayList<Picture> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    constructor(context: Context, items: ArrayList<Picture>) : super() {
        this.context = context
        this.items = items
    }


    interface onClickListener {
        fun onClick(position: Int)
    }

    var onClick: onClickListener? = null

    inner class KImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /** TODO ? = null 처리 관련 (null safety)
         *
         * ? = 문을 붙여 null 문제에 대한 처리가 좋을듯 하다.
         * 하지만 xml과 연동되는 부분은 activity가 죽기 전까지 null되지 않기 때문에 별도 ?처리를 하지 않는다.
         */

        var ivPicture: ImageView
        var tvUri: TextView
        var tvFilename: TextView
        var tvLoc: TextView
        var etDesc: EditText


        init {
            ivPicture = itemView.findViewById(R.id.image_iv)
            tvUri = itemView.findViewById(R.id.uri_tv)
            tvFilename = itemView.findViewById(R.id.filename_tv)
            tvLoc = itemView.findViewById(R.id.loc_tv)
            etDesc = itemView.findViewById(R.id.etc_et)

            itemView.setOnClickListener {
                val position = adapterPosition
                onClick?.onClick(position)
            }
        }
    }


    fun addClickListener(listener: (Int) -> Unit) {
        this.onClick = object : onClickListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        val image = KImageViewHolder(view)

        return image
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: KImageViewHolder, position: Int) {
        val picture = items[position]

        Glide.with(context).load(picture.uri).into(holder.ivPicture)
        holder.tvFilename.text = picture.filename
        holder.tvLoc.text = picture.location
        holder.tvUri.text = picture.uri
        holder.etDesc.setText(picture.desc)

        holder.etDesc.isFocusable = false
        holder.etDesc.isFocusableInTouchMode = false
        holder.etDesc.isClickable = false


        /**
         * textView는 .text로 text를 수정 할 수 있지만,
         * edittext는 .text로 수정할 수 없다. 가져오는 데이터가 다르다.
         */
    }


}