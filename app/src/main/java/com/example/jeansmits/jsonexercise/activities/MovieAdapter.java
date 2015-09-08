package com.example.jeansmits.jsonexercise.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeansmits.jsonexercise.R;
import com.example.jeansmits.jsonexercise.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ContactViewHolder> {
    private static List<Movie> givenMovieList;
    private static Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.givenMovieList = movieList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return givenMovieList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Movie movie = givenMovieList.get(i);
        contactViewHolder.vTitle.setText(movie.getTitle());
        contactViewHolder.vLength.setText("length: " + movie.getLength().toString() + " minutes");
        contactViewHolder.vRating.setText("rating: " + movie.getRating().toString() + "/10");
        contactViewHolder.vDescription.setText(movie.getDescription());
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.movie_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView vTitle;
        protected TextView vLength;
        protected TextView vRating;
        protected TextView vDescription;

        public ContactViewHolder(View v) {
            super(v);
            vTitle =  (TextView) v.findViewById(R.id.title);
            vLength = (TextView)  v.findViewById(R.id.length);
            vRating = (TextView)  v.findViewById(R.id.rating);
            vDescription = (TextView) v.findViewById(R.id.description);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailPageMoviesActivity.class);
            Movie movie = givenMovieList.get(this.getLayoutPosition());
            intent.putExtra("movie_id", movie.getId());
            intent.putExtra("movie_length", movie.getLength());
            intent.putExtra("movie_rating", movie.getRating());
            intent.putExtra("movie_title", movie.getTitle());
            intent.putExtra("movie_description", movie.getDescription());
            context.startActivity(intent);
        }
    }
}
