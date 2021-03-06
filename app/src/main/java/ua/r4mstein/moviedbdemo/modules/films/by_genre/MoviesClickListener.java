package ua.r4mstein.moviedbdemo.modules.films.by_genre;

public interface MoviesClickListener {

    void moviesItemClicked(long movieId);
    void moviesItemLongClicked(long movieId, int position);
    void ratingViewClicked(long movieId, float rating);
    void ratingViewLongClicked(long movieId);
}
