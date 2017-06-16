
package ua.r4mstein.moviedbdemo.data.models.request;

import com.google.gson.annotations.SerializedName;

public class CreateListSendModel {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("name")
    private String mName;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
