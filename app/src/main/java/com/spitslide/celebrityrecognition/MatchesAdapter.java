package com.spitslide.celebrityrecognition;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private final List<Match> data;

    public MatchesAdapter(List<Match> data) {
        this.data = data;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private TextView textView;

        public MatchesViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.name);
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
    public void onBindViewHolder(@NonNull final MatchesViewHolder matchesViewHolder, final int position) {
        insertImage(matchesViewHolder, position, 0);

    }

    private void insertImage(@NonNull final MatchesViewHolder matchesViewHolder, final int position, final int imageNumber) {
        final Match currentItem = data.get(position);
        if (currentItem.getUrls() != null) {
            String currentUrl = currentItem.getUrls().get(imageNumber);
            Picasso.get()
                .load(currentUrl)
                .fit()
                .centerInside()
                .into(matchesViewHolder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        matchesViewHolder.textView.setVisibility(View.VISIBLE);
                        matchesViewHolder.textView.setText(currentItem.getName() + ", " + currentItem.getValue());
                    }

                    @Override
                    public void onError(Exception e) {
                        // try the next image link if the current one didn't work
                        int currentImageNumber = imageNumber + 1;
                        if(currentImageNumber < data.size()) {
                            insertImage(matchesViewHolder, position, currentImageNumber);
                        }


                    }
                });
    }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(int position, Match match) {
        data.set(position, match);
        notifyDataSetChanged();
    }
}
