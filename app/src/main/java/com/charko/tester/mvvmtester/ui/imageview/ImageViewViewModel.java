package com.charko.tester.mvvmtester.ui.imageview;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.util.List;

public class ImageViewViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Picture>> pictures;
    private ImageViewRepository repository;

    public LiveData<List<Picture>> getPictures(Context context) {
        repository = ImageViewRepository.getInstance(context);
        pictures = repository.getPictures();
        return pictures;
    }

    public void updatePicture(int position, Picture picture) {
        if (repository != null) {
            repository.updatePicture(position, picture);
        }
    }
}
