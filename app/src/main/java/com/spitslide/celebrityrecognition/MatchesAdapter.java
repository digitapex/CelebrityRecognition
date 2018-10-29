package com.spitslide.celebrityrecognition;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private final List<String> data;

    public MatchesAdapter(List<String> data) {
        this.data = data;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public MatchesViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
        }
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MatchesViewHolder(layoutInflater.inflate(R.layout.match_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder matchesViewHolder, int position) {
        Picasso.get()
                .load(data.get(position))
                .fit()
                .centerInside()
                .into(matchesViewHolder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("MY", "success");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("MY", e.toString());
                    }
                });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(String newImage) {
        data.add(newImage);
        notifyDataSetChanged();
    }
}
