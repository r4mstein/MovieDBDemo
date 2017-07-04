
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDetailsModel {

    @SerializedName("created_by")
    private String mCreatedBy;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("favorite_count")
    private Long mFavoriteCount;
    @SerializedName("id")
    private String mId;
    @SerializedName("iso_639_1")
    private String mIso6391;
    @SerializedName("item_count")
    private Long mItemCount;
    @SerializedName("items")
    private List<Movie> mMovies;
    @SerializedName("name")
    private String mName;
    @SerializedName("poster_path")
    private Object mPosterPath;

    public String getId() {
        return mId;
    }

    public Long getItemCount() {
        return mItemCount;
    }

    public String getName() {
        return mName;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }
}
