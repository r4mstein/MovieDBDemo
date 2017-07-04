
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class RequestTokenModel {

    @SerializedName("expires_at")
    private String mExpiresAt;
    @SerializedName("request_token")
    private String mRequestToken;
    @SerializedName("success")
    private Boolean mSuccess;

    public String getExpiresAt() {
        return mExpiresAt;
    }

    public String getRequestToken() {
        return mRequestToken;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

}
