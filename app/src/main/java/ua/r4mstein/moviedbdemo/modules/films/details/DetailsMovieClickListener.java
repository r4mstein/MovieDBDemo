package ua.r4mstein.moviedbdemo.modules.films.details;

public interface DetailsMovieClickListener {
    void moviesItemLongClicked(long movieId);
    void ratingViewClicked(long movieId);
    void ratingViewLongClicked(long movieId);
}
