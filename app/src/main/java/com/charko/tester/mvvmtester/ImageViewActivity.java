package com.charko.tester.mvvmtester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charko.tester.mvvmtester.ui.imageview.ImageViewFragment;

public class ImageViewActivity extends AppCompatActivity {

    private ImageViewFragment imageViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_view_activity);

        if (savedInstanceState == null) {
            imageViewFragment = ImageViewFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, imageViewFragment)
                    .commitNow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
