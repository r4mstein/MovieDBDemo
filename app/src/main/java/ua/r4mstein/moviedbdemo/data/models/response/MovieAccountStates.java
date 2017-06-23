
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class MovieAccountStates {

    @SerializedName("favorite")
    private Boolean mFavorite;
    @SerializedName("id")
    private Long mId;
    @SerializedName("rated")
    private Rated mRated;
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

    public Rated getRated() {
        return mRated;
    }

    public void setRated(Rated rated) {
        mRated = rated;
    }

    public Boolean getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        mWatchlist = watchlist;
    }

    public class Rated {

        @SerializedName("value")
        private float mValue;

        public float getValue() {
            return mValue;
        }

        public void setValue(float value) {
            mValue = value;
        }

    }
}
