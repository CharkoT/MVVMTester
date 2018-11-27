package com.charko.tester.mvvmtester.ui.imageview;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;

import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageViewRepository {
    private volatile static ImageViewRepository instance = null;
    private Context context;

    private MutableLiveData<List<Picture>> pictures = new MutableLiveData<>();

    public ImageViewRepository(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<Picture>> getPictures() {
        return pictures;
    }

    public static ImageViewRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (ImageViewRepository.class) {
                instance = new ImageViewRepository(context);
                instance.loadImage();
            }
        }
        return instance;
    }

    private void loadImage() {
        int imageIndex = 0;
        String[] proj = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};
        List<Picture> items = new ArrayList<>();

        Cursor imageCursor = context.getContentResolver().query(
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

            Log.e(">>>>>>>>>>>>", ">>>>>>>>>> getData : " + imageCursor.getString(0));
            Picture picture = ImageMigration(imageCursor.getString(0), imageCursor.getString(1));

            if (picture != null) {
                items.add(picture);
            }
            imageIndex++;

            imageCursor.moveToNext();
        }

        pictures = new MutableLiveData<>();
        ((MutableLiveData<List<Picture>>) pictures).setValue(items);
    }

    private Picture ImageMigration(String uri, String name) {
        try {
            ExifInterface exif = new ExifInterface(uri);
            Picture picture = new Picture(
                    uri,
                    name,
                    "" + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE),
                    0.0,
                    0.0,
                    "");
            return picture;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePicture(int position, Picture picture) {
        List<Picture> updatePicture = pictures.getValue();

        if (updatePicture != null && updatePicture.size() > 0) {
            Picture getPic = updatePicture.get(position);
            getPic.setDesc(picture.getDesc());
        }

        pictures.setValue(updatePicture);
    }
}
