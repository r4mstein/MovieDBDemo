
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesByGenreModel {

    @SerializedName("id")
    private Long mId;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Movie> mMovies;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getId() {
        return mId;
    }

    public Long getPage() {
        return mPage;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

}
