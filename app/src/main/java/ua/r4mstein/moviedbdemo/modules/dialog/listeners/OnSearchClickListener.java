package ua.r4mstein.moviedbdemo.modules.dialog.listeners;

import android.support.v7.widget.RecyclerView;

public interface OnSearchClickListener {

    void onPositiveClicked(String searchRequest, RecyclerView recyclerView);
}
