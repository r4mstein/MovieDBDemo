
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

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getFavoriteCount() {
        return mFavoriteCount;
    }

    public void setFavoriteCount(Long favoriteCount) {
        mFavoriteCount = favoriteCount;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public Long getItemCount() {
        return mItemCount;
    }

    public void setItemCount(Long itemCount) {
        mItemCount = itemCount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(Object posterPath) {
        mPosterPath = posterPath;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
