
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class MovieAccountStatesAlternative {

    @SerializedName("favorite")
    private Boolean mFavorite;
    @SerializedName("id")
    private Long mId;
    @SerializedName("rated")
    private boolean mRated;
    @SerializedName("watchlist")
    private Boolean mWatchlist;

    public Boolean getFavorite() {
        return mFavorite;
    }

    public void setFavorite(Boolean favorite) {
        mFavorite = favorite;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        mWatchlist = watchlist;
    }

    public boolean isRated() {
        return mRated;
    }

    public void setRated(boolean rated) {
        mRated = rated;
    }
}
