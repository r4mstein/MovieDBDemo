
package ua.r4mstein.moviedbdemo.data.models.response;

import com.google.gson.annotations.SerializedName;

public class CreateListModel {

    @SerializedName("list_id")
    private Long mListId;
    @SerializedName("status_code")
    private Long mStatusCode;
    @SerializedName("status_message")
    private String mStatusMessage;
    @SerializedName("success")
    private Boolean mSuccess;

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public Long getListId() {
        return mListId;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }
}
