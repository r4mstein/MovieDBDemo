package ua.r4mstein.moviedbdemo.modules.lists.list_details;

public interface ListDetailsClickListener {

    void itemClicked(long movieId);
    void itemLongClicked(String title, long movieId);
    void ratingViewClicked(long movieId);
}
