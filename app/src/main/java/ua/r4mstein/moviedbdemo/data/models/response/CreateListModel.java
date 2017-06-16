
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

    public Long getListId() {
        return mListId;
    }

    public void setListId(Long listId) {
        mListId = listId;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long statusCode) {
        mStatusCode = statusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        mStatusMessage = statusMessage;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
