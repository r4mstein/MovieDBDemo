
package ua.r4mstein.moviedbdemo.data.models.request;

import com.google.gson.annotations.SerializedName;

public class RateMovieSendModel {

    @SerializedName("value")
    private float mValue;

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }
}
