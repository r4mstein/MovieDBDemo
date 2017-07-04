
package ua.r4mstein.moviedbdemo.data.models.response.movie_details;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry {

    @SerializedName("iso_3166_1")
    private String mIso31661;
    @SerializedName("name")
    private String mName;

    public String getName() {
        return mName;
    }

}
