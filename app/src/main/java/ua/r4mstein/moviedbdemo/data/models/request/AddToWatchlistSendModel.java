
package ua.r4mstein.moviedbdemo.data.models.request;

import com.google.gson.annotations.SerializedName;

public class AddToWatchlistSendModel {

    @SerializedName("media_id")
    private Long mMediaId;
    @SerializedName("media_type")
    private String mMediaType;
    @SerializedName("watchlist")
    private Boolean mWatchlist;

    public Long getMediaId() {
        return mMediaId;
    }

    public void setMediaId(Long mediaId) {
        mMediaId = mediaId;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public Boolean getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        mWatchlist = watchlist;
    }

}
