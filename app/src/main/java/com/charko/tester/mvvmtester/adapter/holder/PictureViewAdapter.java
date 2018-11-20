package com.charko.tester.mvvmtester.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charko.tester.mvvmtester.R;
import com.charko.tester.mvvmtester.simplemodel.Picture;

import java.util.ArrayList;
import java.util.List;

public class PictureViewAdapter extends RecyclerView.Adapter<PictureViewAdapter.PictureViewHolder> {

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPicture;
        private TextView tvUri;
        private TextView tvFilename;
        private TextView tvLoc;
        private EditText etDesc;

        public PictureViewHolder(View itemView) {
            super(itemView);

            ivPicture = itemView.findViewById(R.id.image_iv);
            tvUri = itemView.findViewById(R.id.uri_tv);
            tvFilename = itemView.findViewById(R.id.filename_tv);
            tvLoc = itemView.findViewById(R.id.loc_tv);
            etDesc = itemView.findViewById(R.id.etc_et);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION)
                        onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    private List<Picture> items = new ArrayList<>();

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        Picture picture = items.get(position);
        Glide.with(holder.itemView.getContext()).load(picture.getUri()).into(holder.ivPicture);
        holder.tvUri.setText(picture.getUri().toString());
        holder.tvFilename.setText(picture.getFilename());
        holder.tvLoc.setText(picture.getLocation());
        holder.etDesc.setText(picture.getDesc());
        holder.etDesc.setFocusable(false);
        holder.etDesc.setFocusableInTouchMode(false);
        holder.etDesc.setClickable(false);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Picture> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Picture getItem(int position) {
        return items.get(position);
    }
}
