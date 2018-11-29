package com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.media.ExifInterface
import android.provider.MediaStore
import android.util.Log
import com.charko.tester.mvvmtester.simplemodel.Picture
import java.io.IOException

class KImageViewRepository {

    private var context: Context? = null

    companion object {
        lateinit var instance: KImageViewRepository
            private set
    }
//    private var instance: KImageViewRepository? = null

    var pictures: MutableLiveData<List<Picture>> = MutableLiveData<List<Picture>>()


    fun getInstance(context: Context): KImageViewRepository {
        this.context = context
        instance = this
        instance.loadImage()
        return instance
    }

    fun updatePicture(position: Int, picture: Picture) {
        var updatePicture = pictures.value

        if (updatePicture != null && updatePicture.count() > 0) {
            updatePicture.get(position).desc = picture.desc
        }
        pictures.value = updatePicture
    }

    fun loadImage() {
        if (this.context == null)
            return

        var index = 0

        val proj = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME)
        var items = ArrayList<Picture>()


        val imageCur = context!!.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj,
                null,
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC")


        imageCur.moveToFirst()
        Log.e(">>>>>>>>>>>>", " imageIndex : $index");

        while (!imageCur.isLast) {
            if (index >= 10)
                break

            Log.e(">>>>>>>>>>>>", ">>>>>>>>>>KKK imageCursor.getPosition() : $imageCur.getPosition()")

            if (imageCur.getPosition() < index) {
                imageCur.moveToNext()
                continue
            }

            Log.e(">>>>>>>>>>>>", ">>>>>>>>>>KKK getData : " + imageCur.getString(0))
            val picture = ImageMigration(imageCur.getString(0), imageCur.getString(1))

            if (picture != null) {
                items.add(picture)
            }
            index++

            imageCur.moveToNext()
        }
        pictures.setValue(items)
    }

    private fun ImageMigration(uri: String, name: String): Picture? {
        try {
            val exif = ExifInterface(uri)
            return Picture(
                    uri,
                    name,
                    "" + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE),
                    0.0,
                    0.0,
                    "")
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}
