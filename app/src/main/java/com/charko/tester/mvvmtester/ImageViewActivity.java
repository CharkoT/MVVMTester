package com.charko.tester.mvvmtester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charko.tester.mvvmtester.ui.imageview.ImageViewFragment;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_view_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ImageViewFragment.newInstance())
                    .commitNow();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        setResult(RESULT_OK);
        finish();
    }
}
