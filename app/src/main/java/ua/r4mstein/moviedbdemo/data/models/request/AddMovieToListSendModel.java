
package ua.r4mstein.moviedbdemo.data.models.request;

import com.google.gson.annotations.SerializedName;

public class AddMovieToListSendModel {

    @SerializedName("media_id")
    private Long mMediaId;

    public Long getMediaId() {
        return mMediaId;
    }

    public void setMediaId(Long mediaId) {
        mMediaId = mediaId;
    }

}
