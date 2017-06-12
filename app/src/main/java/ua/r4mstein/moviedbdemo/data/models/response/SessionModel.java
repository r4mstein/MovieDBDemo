
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

    public void setSessionId(String sessionId) {
        mSessionId = sessionId;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
