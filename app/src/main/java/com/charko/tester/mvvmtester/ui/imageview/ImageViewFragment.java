package com.charko.tester.mvvmtester.ui.imageview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charko.tester.mvvmtester.R;
import com.charko.tester.mvvmtester.simplemodel.Picture;

import static android.app.Activity.RESULT_OK;

public class ImageViewFragment extends Fragment {

    private ImageView ivPic;
    private TextView tvUri;
    private TextView tvFilename;
    private TextView tvLoc;
    private EditText etDesc;

    private int savePosition;

    public static ImageViewFragment newInstance() {
        return new ImageViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_view_fragment, container, false);
        Intent intent = getActivity().getIntent();
        Picture picture = intent.getParcelableExtra("picture");
        savePosition = intent.getIntExtra("position", -1);

        ivPic = rootView.findViewById(R.id.image_iv);
        tvUri = rootView.findViewById(R.id.uri_tv);
        tvFilename = rootView.findViewById(R.id.filename_tv);
        tvLoc = rootView.findViewById(R.id.loc_tv);
        etDesc = rootView.findViewById(R.id.etc_et);

        Glide.with(getContext()).load(picture.getUri()).into(ivPic);

        tvUri.setText(picture.getUri());
        tvFilename.setText(picture.getFilename());
        tvLoc.setText(picture.getLocation());

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();

        Intent intent = new Intent();
        String desc = etDesc.getText().toString().trim();

        intent.putExtra("position", savePosition);
        intent.putExtra("desc", desc);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
