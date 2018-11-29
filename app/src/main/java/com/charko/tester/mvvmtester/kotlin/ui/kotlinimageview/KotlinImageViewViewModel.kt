package com.charko.tester.mvvmtester.kotlin.ui.kotlinimageview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.charko.tester.mvvmtester.simplemodel.Picture

class KotlinImageViewViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var pictures: MutableLiveData<List<Picture>>? = null
    var repository: KImageViewRepository? = null

    fun getPictures(context: Context): LiveData<List<Picture>> {
        try {
            if (repository == null)
                repository = KImageViewRepository().getInstance(context)

            pictures = repository?.pictures
        } catch (e: NullPointerException) {
            e.message
        }
        pictures?.let { return pictures as MutableLiveData<List<Picture>> }
    }

    fun update(position: Int, picture: Picture) {
        if (repository != null)
            repository?.updatePicture(position, picture)
    }

}
