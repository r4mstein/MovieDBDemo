
package ua.r4mstein.moviedbdemo.data.models.request;

import com.google.gson.annotations.SerializedName;

public class MarkFavoriteSendModel {

    @SerializedName("favorite")
    private Boolean mFavorite;
    @SerializedName("media_id")
    private Long mMediaId;
    @SerializedName("media_type")
    private String mMediaType;

    public Boolean getFavorite() {
        return mFavorite;
    }

    public void setFavorite(Boolean favorite) {
        mFavorite = favorite;
    }

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

}
