package com.charko.tester.mvvmtester.ui.imageview;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.util.List;

public class ImageViewRepository {
    private volatile static ImageViewRepository instance = null;
    private LiveData<List<Picture>> pictures;
    private Application application;

    public ImageViewRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<Picture>> getPictures() {
        return pictures;
    }

    public static ImageViewRepository getInstance(Application application) {
        if (instance == null) {
            synchronized (ImageViewRepository.class) {
                instance = new ImageViewRepository(application);
                instance.loadImage();
            }
        }
        return instance;
    }

    private void loadImage() {
        int imageIndex = 0;
        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor imageCursor = application.getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                proj, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC");      // 정렬 안 함


        // 이것도 또한 함수로.?
        imageCursor.moveToFirst();
        Log.e(">>>>>>>>>>>>", " imageIndex : " + imageIndex);
        while (!imageCursor.isLast()) {
            if (imageIndex == 10)
                break;

            Log.e(">>>>>>>>>>>>", ">>>>>>>>>> imageCursor.getPosition() : " + imageCursor.getPosition());

            if (imageCursor.getPosition() < imageIndex) {
                imageCursor.moveToNext();
                continue;
            }

//            Image image = new Image();
//
//            image.setFilePath(imageCursor.getString(0));
            Log.e(">>>>>>>>>>>>", ">>>>>>>>>> getData : " + imageCursor.getString(0));
            imageIndex++;
//            image.setId(imageIndex++);


//            images.add(image);
            imageCursor.moveToNext();
        }
    }
}
