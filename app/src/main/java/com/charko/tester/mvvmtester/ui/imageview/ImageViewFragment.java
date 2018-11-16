package com.charko.tester.mvvmtester.ui.imageview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charko.tester.mvvmtester.R;
import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.util.List;

public class ImageViewFragment extends Fragment {

    private ImageViewViewModel mViewModel;

    public static ImageViewFragment newInstance() {
        return new ImageViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(ImageViewViewModel.class);

        // TODO: Use the ViewModel

        mViewModel.getPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable List<Picture> pictures) {
                adapterRefresh();
            }
        });
    }

    private void adapterRefresh() {

        Log.e(">>>>>>>>>>>>>>>>>>>>", ">>>>>>>>>>>>>>>>>> adapterRefresh()");
    }

}
