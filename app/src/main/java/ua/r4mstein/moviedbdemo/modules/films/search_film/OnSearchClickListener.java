package ua.r4mstein.moviedbdemo.modules.films.search_film;

import android.support.v7.widget.RecyclerView;

public interface OnSearchClickListener {

    void onPositiveClicked(String name, RecyclerView recyclerView);
}
