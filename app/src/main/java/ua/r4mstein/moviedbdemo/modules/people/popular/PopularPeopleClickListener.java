package ua.r4mstein.moviedbdemo.modules.people.popular;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;

public interface PopularPeopleClickListener {
    void popularPeopleItemClicked(long movieId);
    void popularPeopleItemLongClicked(long movieId);
    void knownForClicked(List<KnownFor> list);
}
