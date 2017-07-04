
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class SessionModel {

    @SerializedName("session_id")
    private String mSessionId;
    @SerializedName("success")
    private Boolean mSuccess;

    public String getSessionId() {
        return mSessionId;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

}
