
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    @SerializedName("status_code")
    private Long mStatusCode;
    @SerializedName("status_message")
    private String mStatusMessage;

    public Long getStatusCode() {
        return mStatusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

}
