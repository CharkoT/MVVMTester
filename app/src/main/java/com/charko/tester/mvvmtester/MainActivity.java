package com.charko.tester.mvvmtester;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.charko.tester.mvvmtester.adapter.holder.PictureViewAdapter;
import com.charko.tester.mvvmtester.simplemodel.Picture;
import com.charko.tester.mvvmtester.ui.imageview.ImageViewViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageViewViewModel mViewModel;

    private RecyclerView rvMain;
    private PictureViewAdapter pictureViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rvMain = findViewById(R.id.picture_list_rv);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        pictureViewAdapter = new PictureViewAdapter();
        rvMain.setAdapter(pictureViewAdapter);

        // TODO: Main Use the ViewModel
        mViewModel = ViewModelProviders.of(this).get(ImageViewViewModel.class);
        mViewModel.getPictures(getApplicationContext()).observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable List<Picture> pictures) {

                Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>. MainView Init GettingPicture");
                pictureViewAdapter.setItmes(pictures);
            }
        });

        pictureViewAdapter.setItemClickListener(new PictureViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                mViewModel.updatePicture(position, pictureViewAdapter.getItem(position));
                Picture picture = pictureViewAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), ImageViewActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("picture", picture);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Picture picture = data.getParcelableExtra("items");
            if (position >= 0)
                mViewModel.updatePicture(position, picture).observe(this, new Observer<List<Picture>>() {
                    @Override
                    public void onChanged(@Nullable List<Picture> pictures) {
                        Log.e(">>>>>>>>>>>>>", ">>>>>>>>>>>. MainView Edittor Picture");
                        pictureViewAdapter.setItmes(pictures);
                    }
                });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
