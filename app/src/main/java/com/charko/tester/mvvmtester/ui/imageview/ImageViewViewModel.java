package com.charko.tester.mvvmtester.ui.imageview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.util.List;

public class ImageViewViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private LiveData<List<Picture>> pictures;
    private ImageViewRepository repository;

    public ImageViewViewModel(@NonNull Application application) {
        super(application);

        repository = ImageViewRepository.getInstance(application);
        pictures = repository.getPictures();

        Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>>>> ImageViewViewModel oninit");

    }

    public LiveData<List<Picture>> getPictures() {
        if (pictures == null) {
            pictures = new MutableLiveData<>();
        }

        return pictures;
    }
}
